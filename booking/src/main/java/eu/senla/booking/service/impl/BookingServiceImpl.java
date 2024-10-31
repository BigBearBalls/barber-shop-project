package eu.senla.booking.service.impl;

import eu.senla.booking.dto.*;
import eu.senla.booking.entity.Booking;
import eu.senla.booking.entity.MasterTimetable;
import eu.senla.booking.enums.ErrorCode;
import eu.senla.booking.enums.Status;
import eu.senla.booking.exception.LogExceptionWrapper;
import eu.senla.booking.exception.NotFoundException;
import eu.senla.booking.feign.ProcedureClient;
import eu.senla.booking.feign.UserClient;
import eu.senla.booking.mapper.BookingMapper;
import eu.senla.booking.repository.BookingRepository;
import eu.senla.booking.service.BookingService;
import eu.senla.booking.service.MasterTimetableService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import static eu.senla.booking.util.ExceptionMessageTemplateConstants.BOOKING_WITH_ID_NOT_FOUND_MSG_TEMPLATE;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    private final MasterTimetableService masterTimeTableService;

    private final BookingMapper bookingMapper;

    private final UserClient userClient;

    private final ProcedureClient procedureClient;

    @Override
    @Transactional
    public AddBookResponse bookTime(BookTimeRequest request) {
        Booking booking = bookingMapper.toEntity(request);
        MasterTimetable masterTimeTable = masterTimeTableService.getMasterTimeTable(request.masterTimetableId());
        masterTimeTable.setAvailable(false);
        booking.setMasterTimeTable(masterTimeTable);
        bookingRepository.save(booking);
        return new AddBookResponse(booking.getId());
    }

    @Override
    @Transactional
    public void removeBooking(Long bookingId) {
        Booking booking = getBooking(bookingId);
        LocalDate date = booking.getMasterTimeTable().getDate();
        LocalTime time = booking.getMasterTimeTable().getDayTime().getTime();
        if (LocalDateTime.now().isBefore(date.atTime(time))) {
            booking.getMasterTimeTable().setAvailable(true);
            booking.setStatus(Status.CANCELED);
        }
        booking.setStatus(Status.CLOSED);
    }

    @Override
    @Transactional
    public void changeBookingDate(Long bookingId, ChangeBookingDateRequest request) {
        Booking booking = getBooking(bookingId);
        if (request.procedureId() != null) {
            booking.setProcedureId(request.procedureId());
        }
        if (request.masterTimeTableId() != null) {
            MasterTimetable masterTimeTable = masterTimeTableService.getMasterTimeTable(request.masterTimeTableId());
            booking.setMasterTimeTable(masterTimeTable);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public UserBookingsResponse getUserBooks(UUID userId, Pageable pageable) {
        Page<Booking> bookings = bookingRepository.findAllByUserId(userId, pageable);
        List<BookingRecord> list = bookings.stream().map(e -> {
            UserDTO userDTO = userClient.getUser(e.getMasterTimeTable().getMasterId());
            ProcedureDTO procedureDTO = procedureClient.getProcedure(e.getProcedureId());
            return bookingMapper.toDTO(e, userDTO, procedureDTO);
        }).toList();
        return new UserBookingsResponse(list, bookings.getTotalPages());
    }

    @Override
    @Transactional(readOnly = true)
    public BookingRecord getBookingInfoById(Long bookingId) {
        Booking booking = getBooking(bookingId);
        UserDTO userDTO = userClient.getUser(booking.getMasterTimeTable().getMasterId());
        ProcedureDTO procedureDTO = procedureClient.getProcedure(booking.getProcedureId());
        return bookingMapper.toDTO(booking, userDTO, procedureDTO);
    }

    private Booking getBooking(Long bookingId) {
        return bookingRepository.findById(bookingId).orElseThrow(() -> LogExceptionWrapper.logErrorException(
                new NotFoundException(String.format(BOOKING_WITH_ID_NOT_FOUND_MSG_TEMPLATE, bookingId),
                        ErrorCode.ERR_OBJECT_NOT_FOUND)));
    }
}

package eu.senla.booking.service.impl;

import eu.senla.booking.data.mapper.BookingMapper;
import eu.senla.booking.entity.Booking;
import eu.senla.booking.repository.BookingRepository;
import eu.senla.booking.service.BookingService;
import eu.senla.common.booking.dto.request.AggregatedBooking;
import eu.senla.common.booking.dto.response.IdResponseDTO;
import eu.senla.common.booking.dto.response.ResponseWorkingDayDTO;
import eu.senla.common.dto.ProcedureDTO;
import eu.senla.common.enums.ErrorCode;
import eu.senla.common.exception.ExistsException;
import eu.senla.common.exception.LogExceptionWrapper;
import eu.senla.common.exception.MasterNotWorkException;
import eu.senla.common.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    @Transactional
    @Override
    public IdResponseDTO saveBooking(AggregatedBooking aggregatedBookingData) {

        ResponseWorkingDayDTO workingMasterDay = aggregatedBookingData.getWorkingMasterDay();
        ProcedureDTO procedure = aggregatedBookingData.getProcedure();

        checkFreeTime(workingMasterDay, procedure.getDuration(),
                aggregatedBookingData.getBookingRequest().getReservationStart());

        Booking booking = bookingMapper.toBooking(aggregatedBookingData.getBookingRequest(), procedure,
                workingMasterDay, aggregatedBookingData.getBookingRequest().getReservationStart().plusMinutes(procedure.getDuration()));
        bookingRepository.save(booking);
        log.debug("Booking with id: ${} has been created", booking.getId());
        return new IdResponseDTO(booking.getId());
    }

    @Transactional
    @Override
    public Booking findBookingById(UUID id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> LogExceptionWrapper.logErrorException(new NotFoundException(String.format(
                        ErrorCode.ERR_BOOKING_NOT_FOUND.getMessage(), id), ErrorCode.ERR_BOOKING_NOT_FOUND)));
    }

    private void checkFreeTime(ResponseWorkingDayDTO workingDay,
                               Integer duration,
                               LocalTime desiredStartTime) {

        List<Booking> masterBookingsPerDay = bookingRepository
                .findAllByWorkingDayId(workingDay.getId());

        LocalTime workTimeStart = workingDay.getWorkStart();
        LocalTime workTimeEnd = workingDay.getWorkEnd();
        LocalTime desiredEndTime = desiredStartTime.plusMinutes(duration);

        if (desiredStartTime.isBefore(workTimeStart) || desiredEndTime.isAfter(workTimeEnd)) {
            throw LogExceptionWrapper.logErrorException(new MasterNotWorkException(ErrorCode.ERR_MASTER_NOT_WORKING));
        }

        for (Booking booking : masterBookingsPerDay) {
            LocalTime bookingTimeStart = booking.getReservationStart();
            LocalTime bookingTimeEnd = booking.getReservationEnd();

            if (desiredStartTime.isBefore(bookingTimeEnd) && desiredEndTime.isAfter(bookingTimeStart)) {
                throw LogExceptionWrapper.logErrorException(new ExistsException(ErrorCode.ERR_TIME_ALREADY_BOOKED));
            }
        }

    }
}

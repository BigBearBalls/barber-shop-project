package eu.senla.booking.service.impl;

import eu.senla.booking.data.ProcedureDTO;
import eu.senla.booking.data.mapper.BookingMapper;
import eu.senla.booking.data.request.AggregatedBooking;
import eu.senla.booking.data.request.BookingRequestDTO;
import eu.senla.booking.data.response.IdResponseDTO;
import eu.senla.booking.entity.Booking;
import eu.senla.booking.entity.WorkingDay;
import eu.senla.booking.repository.BookingRepository;
import eu.senla.booking.service.BookingService;
import eu.senla.booking.service.exception.MasterNotWorkException;
import eu.senla.booking.service.exception.ResourceNotFoundException;
import eu.senla.booking.service.exception.TimeAlreadyBookedException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;

import static eu.senla.booking.data.response.ErrorMessage.*;

@Service
@AllArgsConstructor
@Slf4j
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    @Transactional
    @Override
    public IdResponseDTO saveBooking(AggregatedBooking aggregatedBookingData, BookingRequestDTO bookingRequestDTO) {

        WorkingDay workingMasterDay = aggregatedBookingData.getWorkingMasterDay();
        ProcedureDTO procedure = aggregatedBookingData.getProcedure();

        List<Booking> masterBookingsPerDay = bookingRepository
                .findAllByWorkingDayId(workingMasterDay.getId());

        checkFreeTime(workingMasterDay, masterBookingsPerDay,
                procedure.getDuration(),
                bookingRequestDTO.getReservationStart());


        Booking booking = bookingMapper.toBooking(bookingRequestDTO, procedure,
                workingMasterDay, bookingRequestDTO.getReservationStart().plusMinutes(procedure.getDuration()));

        bookingRepository.save(booking);
        log.info("Booking with id: ${} has been created", booking.getId());
        return new IdResponseDTO(booking.getId());
    }

    @Transactional
    @Override
    public Booking findBookingById(int id) {
        return (Booking) bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(BOOKING_NOT_FOUND + id));
    }

    private void checkFreeTime(WorkingDay workingDay,
                                  List<Booking> bookings,
                                  Integer duration,
                                  LocalTime desiredStartTime) {

        LocalTime workTimeStart = workingDay.getWorkStart();
        LocalTime workTimeEnd = workingDay.getWorkEnd();
        LocalTime desiredEndTime = desiredStartTime.plusMinutes(duration);

        if(desiredStartTime.isBefore(workTimeStart) || desiredEndTime.isAfter(workTimeEnd)) {
            throw new MasterNotWorkException(MASTER_DOESNT_WORK);
        }

        for (Booking booking : bookings) {
            LocalTime bookingTimeStart = booking.getReservationStart();
            LocalTime bookingTimeEnd = booking.getReservationEnd();

            if(desiredStartTime.isBefore(bookingTimeEnd) && desiredEndTime.isAfter(bookingTimeStart)) {
                throw new TimeAlreadyBookedException(TIME_ALREADY_BOOKED );
            }
        }

    }
}

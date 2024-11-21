package eu.senla.booking.service.impl;

import eu.senla.booking.data.ProcedureDTO;
import eu.senla.booking.data.ResponseWorkingDayDto;
import eu.senla.booking.data.mapper.BookingMapper;
import eu.senla.booking.data.request.AggregatedBooking;
import eu.senla.booking.data.response.IdResponseDTO;
import eu.senla.booking.entity.Booking;
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
import java.util.UUID;

import static eu.senla.booking.data.response.ErrorMessage.*;

@Service
@AllArgsConstructor
@Slf4j
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    @Transactional
    @Override
    public IdResponseDTO saveBooking(AggregatedBooking aggregatedBookingData) {

        ResponseWorkingDayDto workingMasterDay = aggregatedBookingData.getWorkingMasterDay();
        ProcedureDTO procedure = aggregatedBookingData.getProcedure();

        checkFreeTime(workingMasterDay,procedure.getDuration(),
                aggregatedBookingData.getBookingRequest().getReservationStart());

        Booking booking = bookingMapper.toBooking(aggregatedBookingData.getBookingRequest(), procedure,
                workingMasterDay, aggregatedBookingData.getBookingRequest().getReservationStart().plusMinutes(procedure.getDuration()));
        bookingRepository.save(booking);
        log.info("Booking with id: ${} has been created", booking.getId());
        return new IdResponseDTO(booking.getId());
    }

    @Transactional
    @Override
    public Booking findBookingById(UUID id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(BOOKING_NOT_FOUND + id));
    }

    private void checkFreeTime(ResponseWorkingDayDto workingDay,
                               Integer duration,
                               LocalTime desiredStartTime) {

        List<Booking> masterBookingsPerDay = bookingRepository
                .findAllByWorkingDayId(workingDay.getId());

        LocalTime workTimeStart = workingDay.getWorkStart();
        LocalTime workTimeEnd = workingDay.getWorkEnd();
        LocalTime desiredEndTime = desiredStartTime.plusMinutes(duration);

        if (desiredStartTime.isBefore(workTimeStart) || desiredEndTime.isAfter(workTimeEnd)) {
            throw new MasterNotWorkException(MASTER_DOESNT_WORK);
        }

        for (Booking booking : masterBookingsPerDay) {
            LocalTime bookingTimeStart = booking.getReservationStart();
            LocalTime bookingTimeEnd = booking.getReservationEnd();

            if (desiredStartTime.isBefore(bookingTimeEnd) && desiredEndTime.isAfter(bookingTimeStart)) {
                throw new TimeAlreadyBookedException(TIME_ALREADY_BOOKED);
            }
        }

    }
}

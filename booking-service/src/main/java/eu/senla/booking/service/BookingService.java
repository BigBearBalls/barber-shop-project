package eu.senla.booking.service;

import eu.senla.booking.entity.Booking;
import eu.senla.booking.entity.BookingResponseDto;
import eu.senla.booking.entity.TimeSlotResponseDto;

import eu.senla.common.booking.dto.response.IdResponseDTO;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface BookingService {

    IdResponseDTO saveBooking(Booking booking);

    List<TimeSlotResponseDto> findAvailableTimeSlotsDto(UUID meetingRoomId, LocalDate date);

    BookingResponseDto findBookingById(UUID bookingId);

    void delete(UUID id);
}

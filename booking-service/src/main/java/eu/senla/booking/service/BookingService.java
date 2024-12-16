package eu.senla.booking.service;

import eu.senla.booking.entity.Booking;
import eu.senla.common.booking.dto.response.BookingResponseDTO;
import eu.senla.common.booking.dto.response.TimeSlotResponseDTO;

import eu.senla.common.booking.dto.response.IdResponseDTO;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface BookingService {

    IdResponseDTO saveBooking(Booking booking);

    List<TimeSlotResponseDTO> findAvailableTimeSlotsDto(UUID meetingRoomId, LocalDate date);

    BookingResponseDTO findBookingById(UUID bookingId);

    void delete(UUID id);
}

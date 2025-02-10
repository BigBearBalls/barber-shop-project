package eu.senla.booking.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import eu.senla.booking.entity.BookedTimeSlotDto;
import eu.senla.booking.entity.Booking;
import eu.senla.booking.entity.TimeSlot;
import eu.senla.common.booking.dto.request.ChangeBookingStatusDTO;
import eu.senla.common.booking.dto.response.BookingResponseDTO;
import eu.senla.common.booking.dto.response.IdResponseDTO;
import eu.senla.common.booking.dto.response.TimeSlotResponseDTO;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.springframework.data.domain.Page;

public interface BookingService {

    IdResponseDTO saveBooking(Booking booking) throws JsonProcessingException;

    List<TimeSlotResponseDTO> findAvailableTimeSlotsDto(UUID meetingRoomId, LocalDate date);

    Set<BookedTimeSlotDto> findBookedTimeSlotsDto(UUID meetingRoomId, LocalDate date);

    BookingResponseDTO findBookingById(UUID bookingId);

    void delete(UUID id);

    void changeBookingStatus(ChangeBookingStatusDTO dto);

    Set<TimeSlot> findAllBookingsByDateAndMeetingRoom(LocalDate date, UUID meetingRoomId);

    Page<Booking> findBookingsByParams(
            UUID userId,
            LocalDate startDate,
            LocalDate endDate,
            int page,
            int size);
}

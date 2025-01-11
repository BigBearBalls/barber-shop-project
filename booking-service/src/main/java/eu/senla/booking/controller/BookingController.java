package eu.senla.booking.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.booking.data.mapper.BookingMapper;
import eu.senla.booking.entity.BookedTimeSlotDto;
import eu.senla.booking.entity.MeetingRoom;
import eu.senla.booking.service.BookingService;
import eu.senla.booking.service.MeetingRoomService;
import eu.senla.common.booking.dto.request.AllTimeSlotsRequestDto;
import eu.senla.common.booking.dto.request.BookingRequestDTO;
import eu.senla.common.booking.dto.request.ChangeBookingStatusDTO;
import eu.senla.common.booking.dto.response.BookingResponseDTO;
import eu.senla.common.booking.dto.response.IdResponseDTO;
import eu.senla.common.booking.dto.response.TimeSlotResponseDTO;
import eu.senla.common.constant.ValidationConstants;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/bookings")
@AllArgsConstructor
@Validated
public class BookingController {

    private final BookingService bookingService;
    private final BookingMapper bookingMapper;
    private final MeetingRoomService meetingRoomService;

    private final ObjectMapper objectMapper;

    @GetMapping("/{id}")
    public BookingResponseDTO findBookingById(@PathVariable UUID id) {

        return bookingService.findBookingById(id);
    }

    @GetMapping
    public List<TimeSlotResponseDTO> findAvailableTimeSlots(@RequestParam(name = "meetingRoomId")
                                                            @NotNull(message = ValidationConstants.BOOKING_ID_CANNOT_BE_NULL_VALIDATION_MESSAGE)
                                                            UUID meetingRoomId,
                                                            @RequestParam(name = "bookingDate")
                                                            LocalDate bookingDate) {

        return bookingService.findAvailableTimeSlotsDto(meetingRoomId, bookingDate);
    }

    @PostMapping
    public IdResponseDTO save(@RequestBody @Valid BookingRequestDTO bookingRequestDto) throws JsonProcessingException {
        return bookingService.saveBooking(bookingMapper.toBooking(bookingRequestDto));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable
                       @NotNull(message = ValidationConstants.BOOKING_ID_CANNOT_BE_NULL_VALIDATION_MESSAGE)
                       UUID id) {
        bookingService.delete(id);
    }

    @GetMapping("/approvement/{encodedDTO}")
    public void approvementBooking(@PathVariable("encodedDTO") String encodedDTO) throws IOException {
        byte[] decodedDTO = Base64.getDecoder().decode(encodedDTO);
        ChangeBookingStatusDTO dto = objectMapper.readValue(decodedDTO, ChangeBookingStatusDTO.class);
        bookingService.changeBookingStatus(dto);
    }

    @GetMapping("/meeting-rooms")
    public List<MeetingRoom> getAllMeetingRooms() {
        List<MeetingRoom> meetingRooms = meetingRoomService.getAllMeetingRooms();
        log.info(meetingRooms.toString() + "@@@@@@@@@@@@@@@@@@@@@@@@@@");
        return meetingRooms;
    }

    @PostMapping("/booked-time-slots")
    public Set<BookedTimeSlotDto> findBookedTimeSlots(@RequestBody AllTimeSlotsRequestDto allTimeSlotsRequestDto) {
        return bookingService.findBookedTimeSlotsDto(allTimeSlotsRequestDto.getId(), allTimeSlotsRequestDto.getDate());
    }

}

package eu.senla.booking.controller;

import eu.senla.booking.data.mapper.BookingMapper;
import eu.senla.booking.entity.BookingRequestDto;
import eu.senla.booking.entity.BookingResponseDto;
import eu.senla.booking.entity.TimeSlotResponseDto;
import eu.senla.booking.service.BookingService;
import eu.senla.common.booking.dto.response.IdResponseDTO;
import eu.senla.common.constant.ValidationConstants;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bookings")
@AllArgsConstructor
@Validated
public class BookingController {

    private final BookingService bookingService;
    private final BookingMapper bookingMapper;

    @GetMapping("/{id}")
    public BookingResponseDto findBookingById(@PathVariable UUID id) {

        return bookingService.findBookingById(id);
    }

    @GetMapping
    public List<TimeSlotResponseDto> findAvailableTimeSlots(@RequestParam(name = "meetingRoomId")
                                                            @NotNull(message = ValidationConstants.BOOKING_ID_CANNOT_BE_NULL_VALIDATION_MESSAGE)
                                                            UUID meetingRoomId,
                                                            @RequestParam(name = "bookingDate")
                                                            LocalDate bookingDate) {

        return bookingService.findAvailableTimeSlotsDto(meetingRoomId, bookingDate);
    }

    @PostMapping
    public IdResponseDTO save(@RequestHeader(value = "userId", required = false)
                              String userId,
                              @RequestBody
                              @Valid
                              BookingRequestDto bookingRequestDto) {

        System.out.printf("Was the optional header present? %s!%n", (userId == null ? "No" : "Yes"));

        return bookingService.saveBooking(bookingMapper.toBooking(bookingRequestDto));
     }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable
                       @NotNull(message = ValidationConstants.BOOKING_ID_CANNOT_BE_NULL_VALIDATION_MESSAGE)
                       UUID id,
                       @RequestHeader(value = "userId", required = false)
                       String userId) {

        System.out.printf("Was the optional header present? %s!%n", (userId == null ? "No" : "Yes"));

        bookingService.delete(id);
    }
}

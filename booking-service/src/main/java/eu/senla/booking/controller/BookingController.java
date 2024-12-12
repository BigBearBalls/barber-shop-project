package eu.senla.booking.controller;

import eu.senla.booking.data.mapper.BookingMapper;
import eu.senla.booking.data.mapper.TimeSlotMapper;
import eu.senla.booking.entity.Booking;
//import eu.senla.booking.entity.BookingDto;
import eu.senla.booking.entity.BookingRequestDto;
import eu.senla.booking.entity.BookingResponseDto;
import eu.senla.booking.entity.TimeSlot;
//import eu.senla.booking.entity.TimeSlotDto;
//import eu.senla.booking.facade.BookingFacade;
import eu.senla.booking.entity.TimeSlotDto;
//import eu.senla.booking.entity.TimeSlotRequestDto;
import eu.senla.booking.service.BookingService;
//import eu.senla.common.booking.dto.request.BookingRequestDTO;
//import eu.senla.common.booking.dto.response.BookingResponseDTO;
//import eu.senla.common.booking.dto.response.IdResponseDTO;
//import eu.senla.common.constant.ValidationConstants;
//import jakarta.validation.Valid;
//import jakarta.validation.constraints.NotNull;
import eu.senla.common.booking.dto.response.IdResponseDTO;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bookings/")
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
    public List<TimeSlotDto> findAvailableTimeSlots(@RequestParam(name = "bookingId") UUID bookingId,
                                                    @RequestParam(name = "bookingDate") LocalDate bookingDate) {
        //TODO validate

        return bookingService.findAvailableTimeSlotsDto(bookingId, bookingDate);
    }

    @PostMapping
    public IdResponseDTO save(@RequestBody BookingRequestDto bookingRequestDto,
                              @RequestHeader(value = "userId", required = false) String userId) {

        System.out.printf("Was the optional header present? %s!%n", (userId == null ? "No" : "Yes"));

        //TODO validate
        return bookingService.saveBooking(bookingMapper.toBooking(bookingRequestDto));
     }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id,
                       @RequestHeader(value = "userId", required = false) String userId) {

        System.out.printf("Was the optional header present? %s!%n", (userId == null ? "No" : "Yes"));

        bookingService.delete(id);
    }

    @GetMapping("add") //TODO delete later
    @ResponseStatus(HttpStatus.OK)
    public void addTimeSlots() {
        System.out.println("addTimeSlots");
        bookingService.add();
    }


//    private final BookingFacade bookingFacade;
//
//    @PostMapping
//    public IdResponseDTO save(@RequestBody @Valid BookingRequestDTO bookingDTO) {
//        return bookingFacade.saveBooking(bookingDTO);
//    }
//
//    @GetMapping("{id}")
//    public BookingResponseDTO findById(@PathVariable
//                                       @NotNull(message = ValidationConstants.BOOKING_ID_CANNOT_BE_NULL_VALIDATION_MESSAGE)
//                                       UUID id) {
//        return bookingFacade.findById(id);
//    }



}

package eu.senla.booking.controller;

import eu.senla.booking.dto.*;
import eu.senla.booking.service.BookingService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static eu.senla.booking.util.ValidationConstants.BOOKING_ID_CANNOT_BE_LESS_THAN_VALIDATION_MESSAGE;
import static eu.senla.booking.util.ValidationConstants.MIN_ID_VALUE_VALIDATION;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/booking")
@Validated
public class BookingController {

    private final BookingService bookingService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AddBookResponse bookTime(@RequestBody @Valid BookTimeRequest request) {
        return bookingService.bookTime(request);
    }

    @DeleteMapping("/{bookingId}")
    public void removeBooking(@PathVariable
                              @Valid
                              @Min(value = MIN_ID_VALUE_VALIDATION,
                                      message = BOOKING_ID_CANNOT_BE_LESS_THAN_VALIDATION_MESSAGE)
                              Long bookingId) {
        bookingService.removeBooking(bookingId);
    }

    @PutMapping("/{bookingId}")
    public void changeBookingDate(@RequestBody
                                  @Valid
                                  ChangeBookingDateRequest request,
                                  @PathVariable
                                  @Valid
                                  @Min(value = MIN_ID_VALUE_VALIDATION,
                                          message = BOOKING_ID_CANNOT_BE_LESS_THAN_VALIDATION_MESSAGE)
                                  Long bookingId) {
        bookingService.changeBookingDate(bookingId, request);
    }

    @GetMapping("/user/{userId}")
    public UserBookingsResponse getUsersBooks(@PathVariable UUID userId) {
        return bookingService.getUserBooks(userId);
    }

    @GetMapping("/{bookingId}")
    public BookingRecord getBookInfoById(@PathVariable
                                         @Valid
                                         @Min(value = MIN_ID_VALUE_VALIDATION,
                                                 message = BOOKING_ID_CANNOT_BE_LESS_THAN_VALIDATION_MESSAGE)
                                         Long bookingId) {
        return bookingService.getBookingInfoById(bookingId);
    }
}

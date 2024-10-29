package eu.senla.booking.controller;

import eu.senla.booking.dto.*;
import eu.senla.booking.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/booking")
public class BookingController {

    private final BookingService bookingService;

    @GetMapping("/master/{masterId}")
    public GetMasterFreeTimeResponse getMasterFreeTime(@PathVariable UUID masterId) {
        return bookingService.getMasterFreeTime(masterId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AddBookResponse bookTime(@RequestBody BookTimeRequest request) {
        return bookingService.bookTime(request);
    }

    @DeleteMapping("/{bookingId}")
    public void removeBooking(@PathVariable Long bookingId) {
        bookingService.removeBooking(bookingId);
    }

    @PutMapping("/{bookingId}")
    public void changeBookingDate(@RequestBody ChangeBookingDateRequest request,
                                  @PathVariable Long bookingId) {
        bookingService.changeBookingDate(new ChangeBookingDateRequest(bookingId, request.masterFreeTimeId(),
                request.serviceId()));
    }

    @GetMapping("/user/{userId}")
    public UserBookingsResponse getUsersBooks(@PathVariable Long userId) {
        return bookingService.getUserBooks(userId);
    }

    @GetMapping("/{bookingId}")
    public BookingRecord getBookInfoById(@PathVariable Long bookingId) {
        return bookingService.getBookingInfoById(bookingId);
    }

}

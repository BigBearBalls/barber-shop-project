package eu.senla.booking.service;

import eu.senla.booking.dto.*;

import java.util.UUID;

public interface BookingService {

    AddBookResponse bookTime(BookTimeRequest request);

    void removeBooking(Long bookingId);

    void changeBookingDate(Long bookingId, ChangeBookingDateRequest request);

    UserBookingsResponse getUserBooks(Long userId);

    BookingRecord getBookingInfoById(Long bookingId);
}

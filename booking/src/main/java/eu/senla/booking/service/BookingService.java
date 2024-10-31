package eu.senla.booking.service;

import eu.senla.booking.dto.*;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface BookingService {

    AddBookResponse bookTime(BookTimeRequest request);

    void removeBooking(Long bookingId);

    void changeBookingDate(Long bookingId, ChangeBookingDateRequest request);

    UserBookingsResponse getUserBooks(UUID userId, Pageable pageable);

    BookingRecord getBookingInfoById(Long bookingId);
}

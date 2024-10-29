package eu.senla.booking.service.impl;

import eu.senla.booking.dto.*;
import eu.senla.booking.repository.BookingRepository;
import eu.senla.booking.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    @Override
    public GetMasterFreeTimeResponse getMasterFreeTime(UUID masterId) {
        return null;
    }

    @Override
    public AddBookResponse bookTime(BookTimeRequest request) {
        return null;
    }

    @Override
    public void removeBooking(Long bookingId) {

    }

    @Override
    public void changeBookingDate(Long bookingId, ChangeBookingDateRequest request) {

    }

    @Override
    public UserBookingsResponse getUserBooks(Long userId) {
        return null;
    }

    @Override
    public BookingRecord getBookingInfoById(Long bookingId) {
        return null;
    }
}

package eu.senla.booking.service.impl;

import eu.senla.booking.dto.*;
import eu.senla.booking.entity.Booking;
import eu.senla.booking.entity.MasterTimeTable;
import eu.senla.booking.mapper.BookingMapper;
import eu.senla.booking.repository.BookingRepository;
import eu.senla.booking.service.BookingService;
import eu.senla.booking.service.MasterTimeTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    private final MasterTimeTableService masterTimeTableService;

    private final BookingMapper bookingMapper;

    @Override
    public AddBookResponse bookTime(BookTimeRequest request) {
        Booking booking = bookingMapper.toEntity(request);
        MasterTimeTable masterTimeTable = masterTimeTableService.getMasterTimeTable(request.masterFreeTimeId());
        booking.setMasterTimeTable(masterTimeTable);
        bookingRepository.save(booking);
        return new AddBookResponse(booking.getId());
    }

    @Override
    public void removeBooking(Long bookingId) {
        bookingRepository.deleteById(bookingId);
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

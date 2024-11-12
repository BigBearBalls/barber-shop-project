package eu.senla.booking.facade.impl;

import eu.senla.booking.data.request.AggregatedBooking;
import eu.senla.booking.data.request.BookingRequestDTO;
import eu.senla.booking.data.response.BookingResponseDTO;
import eu.senla.booking.data.response.IdResponseDTO;
import eu.senla.booking.entity.Booking;
import eu.senla.booking.facade.BookingDataAggregator;
import eu.senla.booking.facade.BookingFacade;
import eu.senla.booking.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookingFacadeImpl implements BookingFacade {

    private final BookingDataAggregator bookingDataAggregator;
    private final BookingService bookingService;

    @Override
    public IdResponseDTO saveBooking(BookingRequestDTO bookingRequestDto) {

        AggregatedBooking aggregatedBookingData = bookingDataAggregator
                .collectDataForSaving(bookingRequestDto);

        return bookingService
                .saveBooking(aggregatedBookingData, bookingRequestDto);
    }

    @Override
    public BookingResponseDTO findById(int id) {

        Booking foundBooking = bookingService.findBookingById(id);

        return bookingDataAggregator.collectResponseData(foundBooking);
    }
}

package eu.senla.booking.facade;

import eu.senla.booking.data.request.AggregatedBooking;
import eu.senla.booking.data.request.BookingRequestDTO;
import eu.senla.booking.data.response.BookingResponseDTO;
import eu.senla.booking.entity.Booking;

public interface BookingDataAggregator {
    AggregatedBooking collectDataForSaving(BookingRequestDTO bookingRequestDto);
    BookingResponseDTO collectResponseData(Booking booking);
}

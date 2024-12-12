package eu.senla.booking.facade;

import eu.senla.booking.entity.Booking;
import eu.senla.common.booking.dto.request.AggregatedBooking;
import eu.senla.common.booking.dto.request.BookingRequestDTO;
import eu.senla.common.booking.dto.response.BookingResponseDTO;

public interface BookingDataAggregator {
    AggregatedBooking collectDataForSaving(BookingRequestDTO bookingRequestDto);

    BookingResponseDTO collectResponseData(Booking booking);
}

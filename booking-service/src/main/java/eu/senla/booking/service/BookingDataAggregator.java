package eu.senla.booking.service;

import eu.senla.booking.dto.request.AggregatedBooking;
import eu.senla.booking.dto.request.BookingRequestDTO;
import eu.senla.booking.dto.response.BookingResponseDTO;
import eu.senla.booking.entity.Booking;

public interface BookingDataAggregator {
    public AggregatedBooking collectDataForSaving(BookingRequestDTO bookingRequestDto);
    public BookingResponseDTO collectResponseData(Booking booking);
}

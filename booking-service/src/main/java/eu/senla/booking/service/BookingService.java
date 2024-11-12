package eu.senla.booking.service;

import eu.senla.booking.data.request.AggregatedBooking;
import eu.senla.booking.data.request.BookingRequestDTO;
import eu.senla.booking.data.response.IdResponseDTO;
import eu.senla.booking.entity.Booking;

public interface BookingService {
    IdResponseDTO saveBooking(AggregatedBooking aggregatedBookingData, BookingRequestDTO bookingRequestDTO);
    Booking findBookingById(int id);
}

package eu.senla.booking.service;

import eu.senla.booking.dto.request.AggregatedBooking;
import eu.senla.booking.dto.request.BookingRequestDTO;
import eu.senla.booking.dto.response.IdResponseDTO;
import eu.senla.booking.entity.Booking;

public interface BookingService {
    IdResponseDTO saveBooking(AggregatedBooking aggregatedBookingData, BookingRequestDTO bookingRequestDTO);
    Booking findBookingById(int id);
}

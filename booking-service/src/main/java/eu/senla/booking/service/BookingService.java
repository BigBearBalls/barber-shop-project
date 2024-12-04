package eu.senla.booking.service;

import eu.senla.booking.entity.Booking;
import eu.senla.common.booking.dto.request.AggregatedBooking;
import eu.senla.common.booking.dto.response.IdResponseDTO;

import java.util.UUID;

public interface BookingService {
    IdResponseDTO saveBooking(AggregatedBooking aggregatedBookingData);

    Booking findBookingById(UUID id);
}

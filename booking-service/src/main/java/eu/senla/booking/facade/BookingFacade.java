package eu.senla.booking.facade;

import eu.senla.booking.data.request.BookingRequestDTO;
import eu.senla.booking.data.response.BookingResponseDTO;
import eu.senla.booking.data.response.IdResponseDTO;

import java.util.UUID;

public interface BookingFacade {
    IdResponseDTO saveBooking(BookingRequestDTO bookingRequestDto);

    BookingResponseDTO findById(UUID id);
}

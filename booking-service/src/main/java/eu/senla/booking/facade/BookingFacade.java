package eu.senla.booking.facade;

import eu.senla.booking.dto.request.BookingRequestDTO;
import eu.senla.booking.dto.response.BookingResponseDTO;
import eu.senla.booking.dto.response.IdResponseDTO;

public interface BookingFacade {
    IdResponseDTO saveBooking(BookingRequestDTO bookingRequestDto);
    BookingResponseDTO findById(int id);
}

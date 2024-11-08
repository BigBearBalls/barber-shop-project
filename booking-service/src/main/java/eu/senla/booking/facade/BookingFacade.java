package eu.senla.booking.facade;

import eu.senla.booking.data.request.BookingRequestDTO;
import eu.senla.booking.data.response.BookingResponseDTO;
import eu.senla.booking.data.response.IdResponseDTO;

public interface BookingFacade {
    IdResponseDTO saveBooking(BookingRequestDTO bookingRequestDto);
    BookingResponseDTO findById(int id);
}

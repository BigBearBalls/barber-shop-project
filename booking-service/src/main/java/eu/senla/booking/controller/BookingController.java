package eu.senla.booking.controller;

import eu.senla.booking.data.request.BookingRequestDTO;
import eu.senla.booking.data.response.BookingResponseDTO;
import eu.senla.booking.data.response.IdResponseDTO;
import eu.senla.booking.facade.BookingFacade;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")
@AllArgsConstructor
public class BookingController {

    private final BookingFacade bookingFacade;

    @PostMapping
    public ResponseEntity<IdResponseDTO> save(@RequestBody BookingRequestDTO bookingDTO) {
        return ResponseEntity
                .ok( bookingFacade.saveBooking(bookingDTO));
    }

    @GetMapping("{id}")
    public BookingResponseDTO findById(@PathVariable int id) {
        bookingFacade.findById(id);
        return bookingFacade.findById(id);
    }

}

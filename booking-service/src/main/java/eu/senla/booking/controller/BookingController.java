package eu.senla.booking.controller;

import eu.senla.booking.constant.ValidationConstants;
import eu.senla.booking.dto.request.BookingRequestDTO;
import eu.senla.booking.dto.response.BookingResponseDTO;
import eu.senla.booking.dto.response.IdResponseDTO;
import eu.senla.booking.service.BookingFacade;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")
@AllArgsConstructor
@Validated
public class BookingController {

    private final BookingFacade bookingFacade;

    @PostMapping
    public ResponseEntity<IdResponseDTO> save(@RequestBody @Valid BookingRequestDTO bookingDTO) {
        return ResponseEntity
                .ok(bookingFacade.saveBooking(bookingDTO));
    }

    @GetMapping("{id}")
    public BookingResponseDTO findById(@PathVariable
                                       @Min(value = ValidationConstants.MIN_ID_VALUE,
                                            message = ValidationConstants.BOOKING_ID_CANNOT_BE_LESS_THEN_VALIDATION_MESSAGE)
                                       @NotNull(message = ValidationConstants.BOOKING_ID_CANNOT_BE_NULL_VALIDATION_MESSAGE)
                                       Integer id) {
        bookingFacade.findById(id);
        return bookingFacade.findById(id);
    }

}

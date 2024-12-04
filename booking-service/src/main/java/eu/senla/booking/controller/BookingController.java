package eu.senla.booking.controller;

import eu.senla.booking.facade.BookingFacade;
import eu.senla.common.booking.dto.request.BookingRequestDTO;
import eu.senla.common.booking.dto.response.BookingResponseDTO;
import eu.senla.common.booking.dto.response.IdResponseDTO;
import eu.senla.common.constant.ValidationConstants;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/bookings/")
@AllArgsConstructor
@Validated
public class BookingController {

    private final BookingFacade bookingFacade;

    @PostMapping
    public IdResponseDTO save(@RequestBody @Valid BookingRequestDTO bookingDTO) {
        return bookingFacade.saveBooking(bookingDTO);
    }

    @GetMapping("{id}")
    public BookingResponseDTO findById(@PathVariable
                                       @NotNull(message = ValidationConstants.BOOKING_ID_CANNOT_BE_NULL_VALIDATION_MESSAGE)
                                       UUID id) {
        return bookingFacade.findById(id);
    }

}

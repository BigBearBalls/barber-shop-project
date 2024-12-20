package eu.senla.common.booking.dto.request;

import eu.senla.common.booking.enums.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChangeBookingStatusDTO {
    UUID bookingId;
    BookingStatus status;
}

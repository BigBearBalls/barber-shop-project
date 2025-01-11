package eu.senla.common.booking.dto.request;

import eu.senla.common.booking.enums.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatusAndClientRequestDto {

    private BookingStatus status;
    private UUID clientId;

}

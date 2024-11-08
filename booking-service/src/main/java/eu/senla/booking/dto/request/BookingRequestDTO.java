package eu.senla.booking.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequestDTO {

    private Integer procedureId;
    private UUID masterId;
    private UUID clientId;
    private LocalTime reservationStart;
    private LocalDate workingDate;
}

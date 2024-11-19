package eu.senla.booking.data;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
@Builder
public class WorkingDayDto {

    private UUID id;
    private UUID masterId;
    private LocalDate workingDate;
    private LocalTime workStart;
    private LocalTime workEnd;

}

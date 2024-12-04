package eu.senla.booking.data;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ResponseWorkingDayDto {

    private UUID id;
    private UUID masterId;
    private LocalDate workingDate;
    private LocalTime workStart;
    private LocalTime workEnd;

}

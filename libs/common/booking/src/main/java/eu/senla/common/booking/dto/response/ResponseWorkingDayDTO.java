package eu.senla.common.booking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ResponseWorkingDayDTO {

    private UUID id;
    private UUID masterId;
    private LocalDate workingDate;
    private LocalTime workStart;
    private LocalTime workEnd;

}

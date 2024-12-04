package eu.senla.web.dto.authDto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddWorkingDayRequest {

    private String masterId;
    private String workingDate;
    private String workStart;
    private String workEnd;

}

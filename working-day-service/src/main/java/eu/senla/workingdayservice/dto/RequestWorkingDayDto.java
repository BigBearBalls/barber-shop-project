package eu.senla.workingdayservice.dto;

import eu.senla.workingdayservice.util.ValidationConstants;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RequestWorkingDayDto {

    private UUID masterId;
    @NotNull(message = ValidationConstants.WORKING_DATE_CANNOT_BE_NULL_VALIDATION_MESSAGE)
    private LocalDate workingDate;
    @NotNull(message = ValidationConstants.WORKING_DAY_START_TIME_CANNOT_BE_NULL_VALIDATION_MESSAGE)
    private LocalTime workStart;
    @NotNull(message = ValidationConstants.WORKING_DAY_END_TIME_CANNOT_BE_NULL_VALIDATION_MESSAGE)
    private LocalTime workEnd;

}

package eu.senla.workingdayservice.dto;

import eu.senla.workingdayservice.util.ValidationConstants;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class WorkingDayDto {

    @NotNull(message = ValidationConstants.MASTER_ID_CANNOT_BE_NULL_VALIDATION_MESSAGE)
    private UUID masterId;
    @NotNull(message = ValidationConstants.WORKING_DATE_CANNOT_BE_NULL_VALIDATION_MESSAGE)
    private LocalDate workingDate;
    @NotNull(message = ValidationConstants.WORKING_DAY_START_TIME_CANNOT_BE_NULL_VALIDATION_MESSAGE)
    private LocalTime workStart;
    @NotNull(message = ValidationConstants.WORKING_DAY_END_TIME_CANNOT_BE_NULL_VALIDATION_MESSAGE)
    private LocalTime workEnd;
}

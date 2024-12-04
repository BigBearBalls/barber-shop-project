package eu.senla.common.workingday.dto.request;

import eu.senla.common.annotation.validation.WorkingHours;
import eu.senla.common.constant.ValidationConstants;
import eu.senla.common.workingday.dto.annotation.validation.ValidWorkingDay;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ValidWorkingDay
public class RequestWorkingDayDTO {

    @NotNull(message = ValidationConstants.WORKING_MASTER_CANNOT_BE_NULL_VALIDATION_MESSAGE)
    private UUID masterId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "Working date must be today or in the future")
    @NotNull(message = ValidationConstants.WORKING_DATE_CANNOT_BE_NULL_VALIDATION_MESSAGE)
    private LocalDate workingDate;

    @NotNull(message = ValidationConstants.WORKING_DAY_START_TIME_CANNOT_BE_NULL_VALIDATION_MESSAGE)
    @WorkingHours
    private LocalTime workStart;

    @NotNull(message = ValidationConstants.WORKING_DAY_END_TIME_CANNOT_BE_NULL_VALIDATION_MESSAGE)
    @WorkingHours
    private LocalTime workEnd;

}

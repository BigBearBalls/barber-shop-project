package eu.senla.workingdayservice.validation;

import eu.senla.workingdayservice.dto.RequestWorkingDayDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class WorkingDayValidator implements ConstraintValidator<ValidWorkingDay, RequestWorkingDayDto> {

    @Override
    public boolean isValid(RequestWorkingDayDto value, ConstraintValidatorContext context) {
        if (value.getWorkStart() == null || value.getWorkEnd() == null) {
            return true;
        }
        return value.getWorkStart().isBefore(value.getWorkEnd());
    }
}

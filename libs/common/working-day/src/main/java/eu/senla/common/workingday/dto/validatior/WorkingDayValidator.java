package eu.senla.common.workingday.dto.validatior;

import eu.senla.common.workingday.dto.annotation.validation.ValidWorkingDay;
import eu.senla.common.workingday.dto.request.RequestWorkingDayDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class WorkingDayValidator implements ConstraintValidator<ValidWorkingDay, RequestWorkingDayDTO> {

    @Override
    public boolean isValid(RequestWorkingDayDTO value, ConstraintValidatorContext context) {
        if (value.getWorkStart() == null || value.getWorkEnd() == null) {
            return true;
        }
        return value.getWorkStart().isBefore(value.getWorkEnd());
    }
}

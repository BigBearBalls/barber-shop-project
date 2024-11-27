package eu.senla.workingdayservice.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalTime;

public class WorkingTimeValidator implements ConstraintValidator<WorkingTimeConstraint, LocalTime> {

    private static final LocalTime START_TIME = LocalTime.of(9, 0);
    private static final LocalTime END_TIME = LocalTime.of(21, 0);

    @Override
    public boolean isValid(LocalTime value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return !value.isBefore(START_TIME) && !value.isAfter(END_TIME);
    }
}

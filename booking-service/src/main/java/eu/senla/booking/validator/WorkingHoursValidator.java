package eu.senla.booking.validator;

import eu.senla.booking.annotation.validation.WorkingHours;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class WorkingHoursValidator implements ConstraintValidator<WorkingHours, LocalTime> {

    private LocalTime startTime;
    private LocalTime endTime;

    @Override
    public void initialize(WorkingHours constraintAnnotation) {
        try {
            this.startTime = LocalTime.parse(constraintAnnotation.start());
            this.endTime = LocalTime.parse(constraintAnnotation.end());
        }catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid time format in @BusinessHours annotation");
        }
    }

    @Override
    public boolean isValid(LocalTime time, ConstraintValidatorContext constraintValidatorContext) {
        if (time == null) {
            return true;
        }
        return time.isAfter(this.startTime) && time.isBefore(this.endTime);
    }
}

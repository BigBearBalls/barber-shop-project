package eu.senla.workingdayservice.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = WorkingTimeValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface WorkingTimeConstraint {
    String message() default "Working time must be between 09:00 and 21:00";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

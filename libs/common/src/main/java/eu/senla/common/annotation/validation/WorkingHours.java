package eu.senla.common.annotation.validation;

import eu.senla.common.validator.WorkingHoursValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = WorkingHoursValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface WorkingHours {
    String message() default "Reservation start time must be within business hours (e.g., 09:00 to 21:00)";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String start() default "09:00";

    String end() default "21:00";
}

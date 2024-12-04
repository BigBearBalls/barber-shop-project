package eu.senla.common.workingday.dto.annotation.validation;

import eu.senla.common.workingday.dto.validatior.WorkingDayValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = WorkingDayValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidWorkingDay {
    String message() default "Work start time must be before work end time";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

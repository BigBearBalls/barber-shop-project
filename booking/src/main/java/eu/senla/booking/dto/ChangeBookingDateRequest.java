package eu.senla.booking.dto;

import eu.senla.booking.util.ValidationConstants;
import jakarta.validation.constraints.Min;

public record ChangeBookingDateRequest(
        @Min(value = ValidationConstants.MIN_ID_VALUE_VALIDATION,
                message = ValidationConstants.MASTER_TIMETABLE_ID_CANNOT_BE_LESS_THAN_VALIDATION_MESSAGE)
        Long masterTimeTableId,
        @Min(value = ValidationConstants.MIN_ID_VALUE_VALIDATION,
                message = ValidationConstants.PROCEDURE_ID_CANNOT_BE_LESS_THAN_VALIDATION_MESSAGE)
        Long procedureId) {
}

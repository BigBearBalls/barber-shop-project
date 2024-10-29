package eu.senla.booking.dto;

import eu.senla.booking.util.ValidationConstants;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ChangeBookingDateRequest(
        @Min(value = ValidationConstants.MIN_ID_VALUE_VALIDATION,
                message = ValidationConstants.MASTER_FREE_TIME_ID_CANNOT_BE_LESS_THAN_VALIDATION_MESSAGE)
        @NotNull(message = ValidationConstants.MASTER_FREE_TIME_ID_CANNOT_BE_NULL_VALIDATION_MESSAGE)
        Long masterFreeTimeId,
        @Min(value = ValidationConstants.MIN_ID_VALUE_VALIDATION,
                message = ValidationConstants.SERVICE_ID_CANNOT_BE_LESS_THAN_VALIDATION_MESSAGE)
        @NotNull(message = ValidationConstants.SERVICE_ID_CANNOT_BE_NULL_VALIDATION_MESSAGE)
        Long serviceId) {
}

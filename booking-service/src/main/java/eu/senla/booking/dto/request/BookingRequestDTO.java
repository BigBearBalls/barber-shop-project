package eu.senla.booking.dto.request;

import eu.senla.booking.annotation.validation.WorkingHours;
import eu.senla.booking.constant.ValidationConstants;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;
public record BookingRequestDTO(@Min(value = ValidationConstants.MIN_ID_VALUE,
                                     message = ValidationConstants.PROCEDURE_ID_CANNOT_BE_LESS_THEN_VALIDATION_MESSAGE)
                                @NotNull(message = ValidationConstants.PROCEDURE_ID_CANNOT_BE_NULL_VALIDATION_MESSAGE)
                                Integer procedureId,
                                @NotNull(message = ValidationConstants.MASTER_ID_CANNOT_BE_NULL_VALIDATION_MESSAGE)
                                UUID masterId,
                                @NotNull(message = ValidationConstants.CLIENT_ID_CANNOT_BE_NULL_VALIDATION_MESSAGE)
                                UUID clientId,
                                @WorkingHours
                                @NotNull(message = ValidationConstants.RESERVATION_START_TIME_CANNOT_BE_NULL_VALIDATION_MESSAGE)
                                LocalTime reservationStart,
                                @NotNull(message = ValidationConstants.WORKING_DATE_CANNOT_BE_NULL_VALIDATION_MESSAGE)
                                LocalDate workingDate){
    }

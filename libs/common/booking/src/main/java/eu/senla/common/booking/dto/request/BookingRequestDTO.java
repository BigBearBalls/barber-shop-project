package eu.senla.common.booking.dto.request;

import eu.senla.common.annotation.validation.WorkingHours;
import eu.senla.common.constant.ValidationConstants;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequestDTO {
    @NotNull(message = ValidationConstants.PROCEDURE_ID_CANNOT_BE_NULL_VALIDATION_MESSAGE)
    UUID procedureId;

    @NotNull(message = ValidationConstants.MASTER_ID_CANNOT_BE_NULL_VALIDATION_MESSAGE)
    UUID masterId;

    @NotNull(message = ValidationConstants.CLIENT_ID_CANNOT_BE_NULL_VALIDATION_MESSAGE)
    UUID clientId;

    @WorkingHours
    @NotNull(message = ValidationConstants.RESERVATION_START_TIME_CANNOT_BE_NULL_VALIDATION_MESSAGE)
    LocalTime reservationStart;

    @NotNull(message = ValidationConstants.WORKING_DATE_CANNOT_BE_NULL_VALIDATION_MESSAGE)
    LocalDate workingDate;
}

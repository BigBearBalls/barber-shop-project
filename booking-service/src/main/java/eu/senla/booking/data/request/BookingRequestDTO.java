package eu.senla.booking.data.request;

import eu.senla.booking.annotation.validation.WorkingHours;
import eu.senla.booking.constant.ValidationConstants;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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

package eu.senla.common.booking.dto.response;

import eu.senla.common.constant.ValidationConstants;
import jakarta.validation.constraints.NotNull;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TimeSlotResponseDTO {

    @NotNull(message = ValidationConstants.BOOKING_START_TIME_CANNOT_BE_NULL_VALIDATION_MESSAGE)
    private LocalTime reservationStart;
    @NotNull(message = ValidationConstants.BOOKING_END_TIME_CANNOT_BE_NULL_VALIDATION_MESSAGE)
    private LocalTime reservationEnd;
}

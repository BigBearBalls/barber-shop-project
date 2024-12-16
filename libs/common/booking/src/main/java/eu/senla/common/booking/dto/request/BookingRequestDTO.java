package eu.senla.common.booking.dto.request;

import eu.senla.common.constant.ValidationConstants;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequestDTO {

    @NotNull(message = ValidationConstants.BOOKING_ID_CANNOT_BE_NULL_VALIDATION_MESSAGE)
    private UUID meetingRoomId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = ValidationConstants.BOOKING_DATE_CANNOT_BE_NULL_VALIDATION_MESSAGE)
    private LocalDate bookingDate;
    @NotNull(message = ValidationConstants.BOOKING_START_TIME_CANNOT_BE_NULL_VALIDATION_MESSAGE)
    private LocalTime bookingStart;
    @NotNull(message = ValidationConstants.BOOKING_END_TIME_CANNOT_BE_NULL_VALIDATION_MESSAGE)
    private LocalTime bookingEnd;
}

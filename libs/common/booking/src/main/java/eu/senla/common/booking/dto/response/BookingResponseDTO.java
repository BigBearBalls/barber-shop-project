package eu.senla.common.booking.dto.response;

import eu.senla.common.constant.ValidationConstants;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
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
public class BookingResponseDTO {

    @NotNull(message = ValidationConstants.MEETING_ROOM_ID_CANNOT_BE_NULL_VALIDATION_MESSAGE)
    private MeetingRoomResponseDTO meetingRoom;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = ValidationConstants.BOOKING_DATE_CANNOT_BE_NULL_VALIDATION_MESSAGE)
    private LocalDate bookingDate;
    @Valid
    private List<TimeSlotResponseDTO> timeSlots;
}

package eu.senla.booking.entity;

import eu.senla.common.booking.enums.BookingStatus;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookedTimeSlotDto {

    private LocalTime reservationStart;
    private LocalTime reservationEnd;
    private BookingStatus status;
}

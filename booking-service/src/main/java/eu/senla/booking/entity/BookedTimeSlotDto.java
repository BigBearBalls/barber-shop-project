package eu.senla.booking.entity;

import eu.senla.common.booking.dto.request.BookingOwner;
import eu.senla.common.booking.enums.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookedTimeSlotDto {

    private LocalTime reservationStart;
    private LocalTime reservationEnd;
    private BookingStatus status;
    private String teamLeadFirstName;
    private String teamLeadSecondName;
    private BookingOwner bookingOwner;
}

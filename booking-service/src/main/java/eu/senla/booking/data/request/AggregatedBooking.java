package eu.senla.booking.data.request;

import eu.senla.booking.data.ProcedureDTO;
import eu.senla.booking.data.ResponseWorkingDayDto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AggregatedBooking {

    private ResponseWorkingDayDto workingMasterDay;
    private ProcedureDTO procedure;
    private BookingRequestDTO bookingRequest;
}

package eu.senla.booking.data.request;

import eu.senla.booking.data.ProcedureDTO;
import eu.senla.booking.data.ResponseWorkingDayDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AggregatedBooking {

    private ResponseWorkingDayDto workingMasterDay;
    private ProcedureDTO procedure;
    private BookingRequestDTO bookingRequest;
}

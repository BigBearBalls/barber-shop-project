package eu.senla.common.booking.dto.request;

import eu.senla.common.dto.ProcedureDTO;
import eu.senla.common.booking.dto.response.ResponseWorkingDayDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AggregatedBooking {

    private ResponseWorkingDayDTO workingMasterDay;
    private ProcedureDTO procedure;
    private BookingRequestDTO bookingRequest;
}

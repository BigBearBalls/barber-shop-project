package eu.senla.booking.data.request;

import eu.senla.booking.data.ProcedureDTO;
import eu.senla.booking.data.WorkingDayDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AggregatedBooking {

    private WorkingDayDto workingMasterDay;
    private ProcedureDTO procedure;
}

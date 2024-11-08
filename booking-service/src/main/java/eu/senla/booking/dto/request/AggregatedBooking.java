package eu.senla.booking.dto.request;

import eu.senla.booking.dto.ProcedureDTO;
import eu.senla.booking.entity.WorkingDay;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AggregatedBooking {

    private WorkingDay workingMasterDay;
    private ProcedureDTO procedure;
}

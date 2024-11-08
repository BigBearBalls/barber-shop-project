package eu.senla.booking.dto.request;

import eu.senla.booking.entity.WorkingDay;
import eu.senla.booking.mock.ProcedureMock;

public record AggregatedBooking(WorkingDay workingMasterDay, ProcedureMock procedure) {
}

package eu.senla.booking.client;

import eu.senla.booking.dto.ProcedureDTO;

import java.util.UUID;

public interface ProcedureClient {
    ProcedureDTO findProcedureByIdAndMasterId(Integer id, UUID masterId);
}

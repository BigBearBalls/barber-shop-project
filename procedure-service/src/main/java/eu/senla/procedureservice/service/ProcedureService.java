package eu.senla.procedureservice.service;

import eu.senla.common.dto.ProcedureDTO;
import eu.senla.common.procedure.dto.request.CreateProcedureRequest;
import eu.senla.common.procedure.dto.response.IdResponseDTO;

import java.util.List;
import java.util.UUID;

public interface ProcedureService {

    IdResponseDTO save(CreateProcedureRequest request);

    ProcedureDTO findProcedureByIdAndMasterId(UUID id, UUID masterId);

    void assignProcedureToMaster(UUID procedureId, UUID masterId);

    List<ProcedureDTO> getAllProcedures();
}

package eu.senla.procedureservice.service;

import eu.senla.procedureservice.data.dto.request.CreateProcedureRequest;
import eu.senla.procedureservice.data.dto.request.ProcedureDTO;
import eu.senla.procedureservice.data.dto.response.IdResponseDTO;
import eu.senla.procedureservice.data.dto.response.ProceduresPageResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ProcedureService {

    IdResponseDTO save(CreateProcedureRequest request);

    ProcedureDTO findProcedureByIdAndMasterId(UUID id, UUID masterId);

    void assignProcedureToMaster(UUID procedureId, UUID masterId);

    List<ProcedureDTO> getAllProcedures();
}

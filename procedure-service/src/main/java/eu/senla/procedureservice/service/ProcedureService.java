package eu.senla.procedureservice.service;

import eu.senla.procedureservice.data.dto.request.ProcedureDTO;
import eu.senla.procedureservice.data.dto.response.IdResponseDTO;

public interface ProcedureService {

    IdResponseDTO save(ProcedureDTO procedureDTO);
}

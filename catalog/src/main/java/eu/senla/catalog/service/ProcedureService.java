package eu.senla.catalog.service;

import eu.senla.catalog.dto.ProcedureDto;
import java.util.List;

public interface ProcedureService {

    void deleteProcedure(Long id);

    ProcedureDto addProcedure(ProcedureDto procedure);

    List<ProcedureDto> getProcedures();

    ProcedureDto getProcedure(Long procedureId);

    ProcedureDto updateProcedure(Long procedureId, ProcedureDto procedureDto);
}

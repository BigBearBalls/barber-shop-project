package eu.senla.catalog.service;

import eu.senla.catalog.dto.ProcedureDto;
import java.util.List;
import java.util.UUID;

public interface MasterService {

    List<ProcedureDto> getProcedures(UUID masterId);
}

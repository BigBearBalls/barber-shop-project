package eu.senla.catalog.service;

import eu.senla.catalog.entity.Procedure;
import java.util.List;
import java.util.UUID;

public interface MasterService {

    List<Procedure> findProcedures(UUID masterId);
}

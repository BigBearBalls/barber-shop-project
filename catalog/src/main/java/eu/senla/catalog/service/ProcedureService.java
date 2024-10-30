package eu.senla.catalog.service;

import eu.senla.catalog.entity.Procedure;
import java.util.List;

public interface ProcedureService {

    void deleteProcedure(Long id);

    Procedure addProcedure(Procedure procedure);

    List<Procedure> findProcedures();

    Procedure findProcedure(Long procedureId);

    Procedure updateProcedure(Long procedureId, Procedure procedure);
}

package eu.senla.procedureservice.service;

import java.util.UUID;

public interface MasterHasProcedureService {

    boolean checkMasterHasProcedure(Integer procedureId, UUID masterId);

    void subscribeOnProcedure(Integer procedureId, UUID masterId);
}

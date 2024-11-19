package eu.senla.procedureservice.service;

import java.util.UUID;

public interface MasterHasProcedureService {

    boolean checkMasterHasProcedure(UUID procedureId, UUID masterId);

    void subscribeOnProcedure(UUID procedureId, UUID masterId);
}

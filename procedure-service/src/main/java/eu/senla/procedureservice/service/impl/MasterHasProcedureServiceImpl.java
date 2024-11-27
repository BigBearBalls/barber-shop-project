package eu.senla.procedureservice.service.impl;

import eu.senla.procedureservice.data.entity.MasterProcedure;
import eu.senla.procedureservice.data.repository.MasterHasProcedureRepository;
import eu.senla.procedureservice.enums.ErrorCode;
import eu.senla.procedureservice.exception.ExistsException;
import eu.senla.procedureservice.service.MasterHasProcedureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MasterHasProcedureServiceImpl implements MasterHasProcedureService {

    private final MasterHasProcedureRepository masterHasProcedureRepository;

    @Override
    @Transactional
    public boolean checkMasterHasProcedure(UUID procedureId, UUID masterId) {
        return masterHasProcedureRepository.existsByProcedureIdAndMasterId(procedureId, masterId);
    }

    @Override
    @Transactional
    public void subscribeOnProcedure(UUID procedureId, UUID masterId) {
        if (!masterHasProcedureRepository.existsByProcedureIdAndMasterId(procedureId, masterId)) {
            MasterProcedure masterHasProcedure = MasterProcedure.builder()
                    .procedureId(procedureId)
                    .masterId(masterId)
                    .build();
            masterHasProcedureRepository.save(masterHasProcedure);
        } else {
            throw new ExistsException(ErrorCode.ERR_MASTER_PROCEDURE_EXIST.getMessage(), ErrorCode.ERR_MASTER_PROCEDURE_EXIST);
        }
    }
}

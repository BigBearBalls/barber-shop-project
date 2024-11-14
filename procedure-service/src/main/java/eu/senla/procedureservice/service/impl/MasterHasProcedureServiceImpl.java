package eu.senla.procedureservice.service.impl;

import eu.senla.procedureservice.data.entity.MasterHasProcedure;
import eu.senla.procedureservice.data.repository.MasterHasProcedureRepository;
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
    public boolean checkMasterHasProcedure(Integer procedureId, UUID masterId) {
        return masterHasProcedureRepository.existsByProcedureIdAndMasterId(procedureId, masterId);
    }

    @Override
    @Transactional
    public void subscribeOnProcedure(Integer procedureId, UUID masterId) {
        if (!masterHasProcedureRepository.existsByProcedureIdAndMasterId(procedureId, masterId)) {
            MasterHasProcedure masterHasProcedure = MasterHasProcedure.builder()
                    .procedureId(procedureId)
                    .masterId(masterId)
                    .build();
            masterHasProcedureRepository.save(masterHasProcedure);
        }
    }
}

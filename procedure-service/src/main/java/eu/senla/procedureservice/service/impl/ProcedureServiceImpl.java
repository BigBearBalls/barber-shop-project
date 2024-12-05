package eu.senla.procedureservice.service.impl;

import eu.senla.procedureservice.data.dto.request.CreateProcedureRequest;
import eu.senla.procedureservice.data.dto.request.ProcedureDTO;
import eu.senla.procedureservice.data.dto.response.IdResponseDTO;
import eu.senla.procedureservice.data.entity.Procedure;
import eu.senla.procedureservice.data.repository.ProcedureRepository;
import eu.senla.procedureservice.enums.ErrorCode;
import eu.senla.procedureservice.exception.ExistsException;
import eu.senla.procedureservice.exception.LogExceptionWrapper;
import eu.senla.procedureservice.exception.NotFoundException;
import eu.senla.procedureservice.service.MasterHasProcedureService;
import eu.senla.procedureservice.service.ProcedureService;
import eu.senla.procedureservice.service.mapper.ProcedureMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class ProcedureServiceImpl implements ProcedureService {

    private final ProcedureRepository procedureRepository;
    private final ProcedureMapper procedureMapper;
    private final MasterHasProcedureService masterHasProcedureService;

    @Override
    @Transactional
    public IdResponseDTO save(CreateProcedureRequest request) {
        if (procedureRepository.existsByProcedureName(request.getProcedureName())) {
            throw LogExceptionWrapper.logErrorException(new ExistsException(String.format(
                    ErrorCode.ERR_PROCEDURE_ALREADY_EXISTS.getMessage(), "name", request.getProcedureName()),
                    ErrorCode.ERR_PROCEDURE_ALREADY_EXISTS));
        }

        Procedure procedure = procedureMapper.toProcedure(request);
        procedureRepository.save(procedure);

        return new IdResponseDTO(procedure.getId());
    }

    @Override
    @Transactional
    public ProcedureDTO findProcedureByIdAndMasterId(UUID id, UUID masterId) {
        if (!masterHasProcedureService.checkMasterHasProcedure(id, masterId)) {
            throw LogExceptionWrapper.logErrorException(new NotFoundException(
                    ErrorCode.ERR_MASTER_DOESNT_PROVIDE_THIS_PROCEDURE));
        }
        Procedure procedure = procedureRepository.findById(id).orElseThrow(() -> LogExceptionWrapper.logErrorException(
                new NotFoundException(String.format(ErrorCode.ERR_PROCEDURE_NOT_FOUND.getMessage(), "id", id),
                        ErrorCode.ERR_PROCEDURE_NOT_FOUND)));

        return procedureMapper.toProcedureDTO(procedure);
    }

    @Override
    @Transactional
    public void assignProcedureToMaster(UUID procedureId, UUID masterId) {
        if (!procedureRepository.existsById(procedureId)) {
            throw LogExceptionWrapper.logErrorException(new NotFoundException(String.format(
                    ErrorCode.ERR_PROCEDURE_NOT_FOUND.getMessage(), "id", procedureId), ErrorCode.ERR_PROCEDURE_NOT_FOUND));
        }
        masterHasProcedureService.subscribeOnProcedure(procedureId, masterId);
    }

    @Override
    @Transactional
    public List<ProcedureDTO> getAllProcedures() {
        List<Procedure> procedures = procedureRepository.findAll();
        return procedureMapper.toListDTO(procedures);
    }
}

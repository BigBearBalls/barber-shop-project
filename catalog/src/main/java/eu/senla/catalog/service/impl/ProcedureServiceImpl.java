package eu.senla.catalog.service.impl;

import eu.senla.catalog.dto.ProcedureDto;
import eu.senla.catalog.entity.Procedure;
import eu.senla.catalog.exception.EntityExistException;
import eu.senla.catalog.exception.NotFoundByIdException;
import eu.senla.catalog.mapper.ProcedureMapper;
import eu.senla.catalog.repository.ProcedureRepository;
import eu.senla.catalog.service.ProcedureService;
import eu.senla.catalog.util.enums.ExceptionInfo;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProcedureServiceImpl implements ProcedureService {

    private final ProcedureRepository procedureRepository;
    private final ProcedureMapper procedureMapper;

    @Override
    @Transactional
    public void deleteProcedure(Long id) {
        Procedure procedure = procedureRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundByIdException(String.valueOf(id), ExceptionInfo.NOT_FOUND_ID));
        procedureRepository.delete(procedure);
    }

    @Override
    @Transactional
    public ProcedureDto addProcedure(ProcedureDto procedureDto) {
        Procedure excistProcedure = procedureRepository.getByName(procedureDto.name());
        if(excistProcedure != null) {
            throw new EntityExistException(String.valueOf(excistProcedure.getId()), ExceptionInfo.NAME_EXIST);
        }
        return procedureMapper.toProcedureDto(procedureRepository.save(procedureMapper.toProcedure(procedureDto)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProcedureDto> getProcedures() {
        return procedureRepository
                .findAll()
                .stream()
                .map(procedureMapper::toProcedureDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProcedureDto getProcedure(Long procedureId) {
        Procedure procedure = procedureRepository
                .findById(procedureId)
                .orElseThrow(() -> new NotFoundByIdException(String.valueOf(procedureId), ExceptionInfo.NOT_FOUND_ID));
        return procedureMapper.toProcedureDto(procedure);
    }

    @Override
    @Transactional
    public ProcedureDto updateProcedure(Long procedureId, ProcedureDto updateProcedureDto) {
        Procedure currantProcedure = procedureMapper.toProcedure(getProcedure(procedureId));
        Optional.ofNullable(updateProcedureDto.name()).ifPresent(currantProcedure::setName);
        Optional.of(updateProcedureDto.price()).ifPresent(currantProcedure::setPrice);
        return procedureMapper.toProcedureDto(procedureRepository.save(currantProcedure));
    }
}

package eu.senla.catalog.service.impl;

import eu.senla.catalog.entity.Procedure;
import eu.senla.catalog.exception.ProcedureExistException;
import eu.senla.catalog.exception.ProcedureNotFoundByIdException;
import eu.senla.catalog.repository.ProcedureRepository;
import eu.senla.catalog.service.ProcedureService;
import eu.senla.catalog.util.enums.ExceptionInfo;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProcedureServiceImpl implements ProcedureService {

    private final ProcedureRepository procedureRepository;

    @Override
    public void deleteProcedure(Long id) {
        if(procedureRepository.findById(id).isEmpty()) {
            throw new ProcedureNotFoundByIdException(id, ExceptionInfo.NOT_FOUND_ID);
        }
        procedureRepository.deleteById(id);
    }

    @Override
    public Procedure addProcedure(Procedure procedure) {
        if (procedureRepository.existsByName(procedure.getName())) {
            Procedure excistProcedure = procedureRepository.findByName(procedure.getName());
            throw new ProcedureExistException(excistProcedure.getId(), ExceptionInfo.NAME_EXIST);
        }
        return procedureRepository.save(procedure);
    }

    @Override
    public List<Procedure> findProcedures() {
        return procedureRepository.findAll();
    }

    @Override
    public Procedure findProcedure(Long procedureId) {
        Optional<Procedure> procedure = procedureRepository.findById(procedureId);
        if(procedure.isEmpty()) {
            throw new ProcedureNotFoundByIdException(procedureId, ExceptionInfo.NOT_FOUND_ID);
        }
        return procedure.get();
    }

    @Override
    public Procedure updateProcedure(Long procedureId, Procedure updateProcedure) {
        Procedure currantProcedure = findProcedure(procedureId);
        Optional.ofNullable(updateProcedure.getName()).ifPresent(currantProcedure::setName);
        Optional.of(updateProcedure.getPrice()).ifPresent(currantProcedure::setPrice);
        return procedureRepository.save(currantProcedure);
    }
}

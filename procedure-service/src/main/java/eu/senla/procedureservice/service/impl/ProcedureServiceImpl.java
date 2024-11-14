package eu.senla.procedureservice.service.impl;

import eu.senla.procedureservice.data.dto.request.ProcedureDTO;
import eu.senla.procedureservice.data.dto.response.IdResponseDTO;
import eu.senla.procedureservice.data.entity.Procedure;
import eu.senla.procedureservice.data.repository.ProcedureRepository;
import eu.senla.procedureservice.service.ProcedureService;
import eu.senla.procedureservice.service.exception.ResourceAlreadyExistException;
import eu.senla.procedureservice.service.mapper.ProcedureMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static eu.senla.procedureservice.data.dto.response.ErrorMessage.PROCEDURE_ALREADY_EXISTS;

@Service
@AllArgsConstructor
@Slf4j
public class ProcedureServiceImpl implements ProcedureService {

    private final ProcedureRepository procedureRepository;
    private final ProcedureMapper procedureMapper;

    // todo
    //  add mapper
    //  add exception
    //
    @Override
    @Transactional
    public IdResponseDTO save(ProcedureDTO procedureDTO) {

        if(procedureRepository
                .existsByProcedureName(procedureDTO.getProcedureName())) {
           throw new ResourceAlreadyExistException(PROCEDURE_ALREADY_EXISTS);
        }


        Procedure procedure =  procedureMapper.toProcedure(procedureDTO);
        procedureRepository.save(procedure);

        log.info("Procedure saved with {} successfully", procedure.getId());

        return new IdResponseDTO(procedure.getId());
    }
}

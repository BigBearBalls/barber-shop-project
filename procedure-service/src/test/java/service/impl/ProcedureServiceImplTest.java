package service.impl;

import eu.senla.common.dto.ProcedureDTO;
import eu.senla.common.exception.ExistsException;
import eu.senla.common.exception.NotFoundException;
import eu.senla.common.procedure.dto.request.CreateProcedureRequest;
import eu.senla.common.procedure.dto.response.IdResponseDTO;
import eu.senla.procedureservice.data.entity.Procedure;
import eu.senla.procedureservice.data.repository.ProcedureRepository;
import eu.senla.procedureservice.service.MasterHasProcedureService;
import eu.senla.procedureservice.service.impl.ProcedureServiceImpl;
import eu.senla.procedureservice.service.mapper.ProcedureMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProcedureServiceImplTest {

    @Mock
    private ProcedureRepository procedureRepository;

    @Mock
    private ProcedureMapper procedureMapper;

    @Mock
    private MasterHasProcedureService masterHasProcedureService;

    @InjectMocks
    private ProcedureServiceImpl procedureService;

    @Test
    public void save_shouldSaveAndReturnIdResponseDto() {

        CreateProcedureRequest createProcedureRequest = new CreateProcedureRequest("Стрижка",
                new BigDecimal(100), 30);

        Procedure procedure = new Procedure(UUID.randomUUID(), "Стрижка", new BigDecimal(100),
                30);

        when(procedureRepository.existsByProcedureName(createProcedureRequest.getProcedureName())).thenReturn(false);
        when(procedureMapper.toProcedure(createProcedureRequest)).thenReturn(procedure);
        IdResponseDTO idResponseDTO = procedureService.save(createProcedureRequest);

        verify(procedureMapper, times(1)).toProcedure(createProcedureRequest);
        verify(procedureRepository, times(1)).save(procedure);
        assertEquals(idResponseDTO.getId(), procedure.getId());
    }

    @Test
    public void save_shouldReturnException() {

        CreateProcedureRequest createProcedureRequest = new CreateProcedureRequest("Стрижка",
                new BigDecimal(100), 30);

        Procedure procedure = new Procedure(UUID.randomUUID(), "Стрижка", new BigDecimal(100),
                30);

        when(procedureRepository.existsByProcedureName(createProcedureRequest.getProcedureName())).thenReturn(true);
        ExistsException exception = assertThrows(
                ExistsException.class,
                () -> procedureService.save(createProcedureRequest),
                "Expected to throw ExistsException, but it didn't"
        );

        verify(procedureRepository, times(1)).existsByProcedureName(createProcedureRequest.getProcedureName());
        assertEquals(String.format("Procedure with name '%s' already exists!", procedure.getProcedureName()), exception.getMessage());
    }

    @Test
    public void findProcedureByIdAndMasterId_shouldReturnProcedureDto() {

        UUID randomId = UUID.randomUUID();
        UUID secondRandomId = UUID.randomUUID();

        Procedure procedure = new Procedure(randomId, "Стрижка", new BigDecimal(100),
                30);

        ProcedureDTO procedureDTO = new ProcedureDTO(UUID.randomUUID(), "Стрижка", new BigDecimal(100),
                30);

        when(masterHasProcedureService.checkMasterHasProcedure(randomId, secondRandomId))
                .thenReturn(true);

        when(procedureRepository.findById(procedure.getId())).thenReturn(Optional.of(procedure));
        when(procedureMapper.toProcedureDTO(procedure)).thenReturn(procedureDTO);

        ProcedureDTO returnedProcedureDTO = procedureService.findProcedureByIdAndMasterId(randomId, secondRandomId);

        verify(procedureRepository, times(1)).findById(procedure.getId());
        verify(procedureMapper, times(1)).toProcedureDTO(procedure);
        assertEquals(procedureDTO, returnedProcedureDTO);
    }

    @Test
    public void findProcedureByIdAndMasterId_shouldReturnNotFoundException_masterDoesntProvideTheProcedure() {

        UUID randomId = UUID.randomUUID();
        UUID secondRandomId = UUID.randomUUID();

        when(masterHasProcedureService.checkMasterHasProcedure(randomId, secondRandomId))
                .thenReturn(false);

        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> procedureService.findProcedureByIdAndMasterId(randomId, secondRandomId),
                "Expected to throw NotFoundException, but it didn't"
        );

        verify(masterHasProcedureService, times(1)).checkMasterHasProcedure(randomId, secondRandomId);
        assertEquals("Master does not provide this procedure!", exception.getMessage());
    }

    @Test
    public void findProcedureByIdAndMasterId_shouldReturnNotFoundException_procedureNotFound() {

        UUID randomId = UUID.randomUUID();
        UUID secondRandomId = UUID.randomUUID();

        Procedure procedure = new Procedure(randomId, "Стрижка", new BigDecimal(100),
                30);

        when(masterHasProcedureService.checkMasterHasProcedure(randomId, secondRandomId))
                .thenReturn(true);

        when(procedureRepository.findById(procedure.getId())).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> procedureService.findProcedureByIdAndMasterId(randomId, secondRandomId),
                "Expected to throw NotFoundException, but it didn't"
        );

        verify(masterHasProcedureService, times(1)).checkMasterHasProcedure(randomId, secondRandomId);
        verify(procedureRepository, times(1)).findById(procedure.getId());
        assertEquals(String.format("Procedure with id '%s' was not found!", procedure.getId()), exception.getMessage());
    }

    @Test
    public void assignProcedureToMaster_shouldReturnSuccess() {

        UUID randomId = UUID.randomUUID();
        UUID secondRandomId = UUID.randomUUID();

        when(procedureRepository.existsById(randomId)).thenReturn(false);

        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> procedureService.assignProcedureToMaster(randomId, secondRandomId),
                "Expected to throw NotFoundException, but it didn't"
        );

        verify(procedureRepository, times(1)).existsById(randomId);
        assertEquals(String.format("Procedure with id '%s' was not found!", randomId), exception.getMessage());
    }
}

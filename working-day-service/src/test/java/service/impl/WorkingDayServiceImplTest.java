package service.impl;

import eu.senla.workingdayservice.dto.RequestWorkingDayDto;
import eu.senla.workingdayservice.dto.ResponseWorkingDayDto;
import eu.senla.workingdayservice.entity.WorkingDay;
import eu.senla.workingdayservice.exception.NotFoundByDateAndByIdException;
import eu.senla.workingdayservice.exception.NotFoundByIdException;
import eu.senla.workingdayservice.mapper.WorkingDayMapper;
import eu.senla.workingdayservice.repository.WorkingDayRepository;
import eu.senla.workingdayservice.service.impl.WorkingDayServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WorkingDayServiceImplTest {

    @InjectMocks
    private WorkingDayServiceImpl workingDayService;

    @Mock
    private WorkingDayMapper workingDayMapper;

    @Mock
    private WorkingDayRepository workingDayRepository;

    @Test
    public void findById_shouldReturnWorkingDayDto() {

        UUID id = UUID.randomUUID();
        WorkingDay workingDay = new WorkingDay(UUID.randomUUID(), UUID.randomUUID(),
                LocalDate.of(2024, 12, 15), LocalTime.of(10, 0), LocalTime.of(18, 0));
        ResponseWorkingDayDto workingDayDto = new ResponseWorkingDayDto(UUID.randomUUID(), UUID.randomUUID(),
                LocalDate.of(2024, 12, 15), LocalTime.of(10, 0), LocalTime.of(18, 0));

        when(workingDayMapper.toWorkingDayDto(workingDay)).thenReturn(workingDayDto);
        when(workingDayRepository.findById(id)).thenReturn(Optional.of(workingDay));

        ResponseWorkingDayDto result = workingDayService.findById(id);

        verify(workingDayMapper, times(1)).toWorkingDayDto(workingDay);
        verify(workingDayRepository, times(1)).findById(id);
        assertEquals(workingDayDto, result);
    }

    @Test
    public void findById_shouldReturnException() {

        UUID id = UUID.randomUUID();
        when(workingDayRepository.findById(id)).thenReturn(Optional.empty());

        NotFoundByIdException exception = assertThrows(
                NotFoundByIdException.class,
                () -> workingDayService.findById(id),
                "Expected to throw NotFoundByIdException, but it didn't"
        );

        verify(workingDayRepository, times(1)).findById(id);
        assertEquals("Working day couldn't be found by date", exception.getMessage());
    }

    @Test
    public void save_shouldReturnSavedId() {

        WorkingDay workingDay = new WorkingDay(UUID.randomUUID(), UUID.randomUUID(),
                LocalDate.of(2024, 12, 15), LocalTime.of(10, 0), LocalTime.of(18, 0));

        RequestWorkingDayDto requestWorkingDayDto = new RequestWorkingDayDto(UUID.randomUUID(),
                LocalDate.of(2024, 12, 15), LocalTime.of(10, 0), LocalTime.of(18, 0));

        when(workingDayMapper.toWorkingDay(requestWorkingDayDto)).thenReturn(workingDay);
        when(workingDayRepository.existsWorkingDayByMasterIdAndWorkingDate(workingDay.getMasterId(), workingDay.getWorkingDate())).thenReturn(false);
        when(workingDayRepository.save(workingDay)).thenReturn(workingDay);

        UUID result = workingDayService.save(requestWorkingDayDto);
        verify(workingDayMapper, times(1)).toWorkingDay(requestWorkingDayDto);
        verify(workingDayRepository, times(1)).existsWorkingDayByMasterIdAndWorkingDate(workingDay.getMasterId(), workingDay.getWorkingDate());
        verify(workingDayRepository, times(1)).save(workingDay);
        assertEquals(workingDay.getId(), result);
    }

    @Test
    public void save_shouldReturnException() {

        WorkingDay workingDay = new WorkingDay(UUID.randomUUID(), UUID.randomUUID(),
                LocalDate.of(2024, 12, 15), LocalTime.of(10, 0), LocalTime.of(18, 0));
        RequestWorkingDayDto requestWorkingDayDto = new RequestWorkingDayDto(UUID.randomUUID(),
                LocalDate.of(2024, 12, 15), LocalTime.of(10, 0), LocalTime.of(18, 0));

        when(workingDayMapper.toWorkingDay(requestWorkingDayDto)).thenReturn(workingDay);
        when(workingDayRepository.existsWorkingDayByMasterIdAndWorkingDate(workingDay.getMasterId(), workingDay.getWorkingDate())).thenReturn(true);

        NotFoundByDateAndByIdException exception = assertThrows(
                NotFoundByDateAndByIdException.class,
                () -> workingDayService.save(requestWorkingDayDto),
                "Expected to throw NotFoundByDateAndByIdException, but it didn't"
        );
        verify(workingDayMapper, times(1)).toWorkingDay(requestWorkingDayDto);
        verify(workingDayRepository, times(1)).existsWorkingDayByMasterIdAndWorkingDate(workingDay.getMasterId(),
                workingDay.getWorkingDate());
        assertEquals("This master doesnt work at date you are looking for", exception.getMessage());
    }

    @Test
    public void findByMasterAndWorkingDate_shouldReturnWorkingDayDto() {

        UUID id = UUID.randomUUID();
        LocalDate date = LocalDate.of(2024, 12, 15);
        WorkingDay workingDay = new WorkingDay(UUID.randomUUID(), UUID.randomUUID(),
                LocalDate.of(2024, 12, 15), LocalTime.of(10, 0), LocalTime.of(18, 0));
        ResponseWorkingDayDto workingDayDto = new ResponseWorkingDayDto(UUID.randomUUID(), UUID.randomUUID(),
                LocalDate.of(2024, 12, 15), LocalTime.of(10, 0), LocalTime.of(18, 0));

        when(workingDayRepository.getWorkingDayByMasterIdAndWorkingDate(id, date)).thenReturn(Optional.of(workingDay));
        when(workingDayMapper.toWorkingDayDto(workingDay)).thenReturn(workingDayDto);

        ResponseWorkingDayDto result = workingDayService.findByMasterAndWorkingDate(id, date);

        verify(workingDayRepository, times(1)).getWorkingDayByMasterIdAndWorkingDate(id, date);
        verify(workingDayMapper, times(1)).toWorkingDayDto(workingDay);
        assertEquals(workingDayDto, result);
    }

    @Test
    public void findByMasterAndWorkingDate_shouldReturnException() {

        UUID id = UUID.randomUUID();
        LocalDate date = LocalDate.of(2024, 12, 15);

        when(workingDayRepository.getWorkingDayByMasterIdAndWorkingDate(id, date)).thenReturn(Optional.empty());

        NotFoundByDateAndByIdException exception = assertThrows(
                NotFoundByDateAndByIdException.class,
                () -> workingDayService.findByMasterAndWorkingDate(id, date),
                "Expected to throw NotFoundByDateAndByIdException, but it didn't"
        );
        verify(workingDayRepository, times(1)).getWorkingDayByMasterIdAndWorkingDate(id, date);
        assertEquals("This master doesnt work at date you are looking for", exception.getMessage());
    }

}

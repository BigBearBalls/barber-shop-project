package eu.senla.workingdayservice.service;

import eu.senla.workingdayservice.dto.WorkingDayDto;
import eu.senla.workingdayservice.entity.WorkingDay;
import eu.senla.workingdayservice.exception.NotFoundByIdException;
import eu.senla.workingdayservice.mapper.WorkingDayMapper;
import eu.senla.workingdayservice.repository.WorkingDayRepository;
import eu.senla.workingdayservice.util.ExceptionInfo;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WorkingDayServiceImpl implements WorkingDayService {

    private final WorkingDayRepository workingDayRepository;
    private final WorkingDayMapper workingDayMapper;

    public WorkingDayServiceImpl(WorkingDayRepository workingDayRepository, WorkingDayMapper workingDayMapper) {
        this.workingDayRepository = workingDayRepository;
        this.workingDayMapper = workingDayMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public WorkingDayDto findById(int id) {
            return workingDayMapper
                    .toWorkingDayDto(workingDayRepository
                            .findById(id)
                            .orElseThrow(() -> new NotFoundByIdException(ExceptionInfo.WORKING_DAY_NOT_FOUND_BY_ID.getExceptionCode(),
                                                                         ExceptionInfo.WORKING_DAY_NOT_FOUND_BY_ID.getExceptionMessage())));
    }

    @Override
    public Integer save(WorkingDayDto workingDayDto) {
        WorkingDay workingDay = workingDayMapper.toWorkingDay(workingDayDto);

        workingDayRepository
                .getByDate(workingDay.getWorkingDate())
                .orElseThrow(() -> new NotFoundByIdException(ExceptionInfo.WORKING_DAY_NOT_FOUND_BY_DATE.getExceptionCode(),
                                                             ExceptionInfo.WORKING_DAY_NOT_FOUND_BY_DATE.getExceptionMessage()));

        return workingDayRepository.save(workingDay).getId();
    }

    @Override
    @Transactional(readOnly = true)
    public WorkingDayDto findByMasterAndWorkingDate(UUID masterId, LocalDate workingDate) {

        return workingDayMapper
                .toWorkingDayDto(workingDayRepository
                        .getWorkingDayByMasterAndWorkingDate(masterId, workingDate)
                        .orElseThrow(() -> new NotFoundByIdException(ExceptionInfo.WORKING_DAY_NOT_FOUND_BY_ID_DATE.getExceptionCode(),
                                                                     ExceptionInfo.WORKING_DAY_NOT_FOUND_BY_ID_DATE.getExceptionMessage())));
    }
}

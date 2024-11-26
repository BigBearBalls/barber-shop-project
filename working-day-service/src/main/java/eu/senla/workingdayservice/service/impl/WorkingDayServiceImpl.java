package eu.senla.workingdayservice.service.impl;

import eu.senla.workingdayservice.dto.RequestWorkingDayDto;
import eu.senla.workingdayservice.dto.ResponseWorkingDayDto;
import eu.senla.workingdayservice.entity.WorkingDay;
import eu.senla.workingdayservice.exception.NotFoundByDateAndByIdException;
import eu.senla.workingdayservice.exception.NotFoundByIdException;
import eu.senla.workingdayservice.mapper.WorkingDayMapper;
import eu.senla.workingdayservice.repository.WorkingDayRepository;
import eu.senla.workingdayservice.service.WorkingDayService;
import eu.senla.workingdayservice.util.ExceptionInfo;
import java.time.LocalDate;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WorkingDayServiceImpl implements WorkingDayService {

    private final WorkingDayRepository workingDayRepository;
    private final WorkingDayMapper workingDayMapper;

    @Override
    @Transactional(readOnly = true)
    public ResponseWorkingDayDto findById(UUID id) {
            return workingDayMapper.toWorkingDayDto(workingDayRepository
                    .findById(id)
                    .orElseThrow(() -> new NotFoundByIdException(ExceptionInfo.WORKING_DAY_NOT_FOUND_BY_ID.getExceptionCode(),
                                                                 ExceptionInfo.WORKING_DAY_NOT_FOUND_BY_ID.getExceptionMessage())));
    }

    @Override
    @Transactional
    public UUID save(RequestWorkingDayDto requestWorkingDayDto) {

        WorkingDay workingDay = workingDayMapper.toWorkingDay(requestWorkingDayDto);

        if(workingDayRepository.existsWorkingDayByMasterIdAndWorkingDate(workingDay.getMasterId(), workingDay.getWorkingDate())) {
            throw new NotFoundByDateAndByIdException(ExceptionInfo.WORKING_DAY_ALREADY_EXIST.getExceptionCode(),
                    ExceptionInfo.WORKING_DAY_ALREADY_EXIST.getExceptionMessage());
        }

        return workingDayRepository.save(workingDay).getId();
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseWorkingDayDto findByMasterAndWorkingDate(UUID masterId, LocalDate workingDate) {

        return workingDayMapper
                .toWorkingDayDto(workingDayRepository
                        .getWorkingDayByMasterIdAndWorkingDate(masterId, workingDate)
                        .orElseThrow(() -> new NotFoundByDateAndByIdException(ExceptionInfo.WORKING_DAY_NOT_FOUND_BY_ID_DATE.getExceptionCode(),
                                                                     ExceptionInfo.WORKING_DAY_NOT_FOUND_BY_ID_DATE.getExceptionMessage())));
    }
}

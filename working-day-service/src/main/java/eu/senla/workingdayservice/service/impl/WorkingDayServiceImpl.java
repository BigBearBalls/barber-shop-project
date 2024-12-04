package eu.senla.workingdayservice.service.impl;

import eu.senla.common.enums.ErrorCode;
import eu.senla.common.exception.ExistsException;
import eu.senla.common.exception.LogExceptionWrapper;
import eu.senla.common.exception.NotFoundException;
import eu.senla.common.workingday.dto.request.CalendarFeignData;
import eu.senla.common.workingday.dto.response.ResponseWorkingDayDTO;
import eu.senla.workingdayservice.entity.WorkingDay;
import eu.senla.workingdayservice.mapper.WorkingDayMapper;
import eu.senla.workingdayservice.repository.WorkingDayRepository;
import eu.senla.workingdayservice.service.WorkingDayService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WorkingDayServiceImpl implements WorkingDayService {

    private final WorkingDayRepository workingDayRepository;
    private final WorkingDayMapper workingDayMapper;

    @Override
    @Transactional(readOnly = true)
    public ResponseWorkingDayDTO findById(UUID id) {
        return workingDayMapper.toWorkingDayDto(workingDayRepository.findById(id).orElseThrow(() -> LogExceptionWrapper
                .logErrorException(new NotFoundException(String.format(ErrorCode.ERR_WORKING_DAY_NOT_FOUND.getMessage(),
                        "id", id), ErrorCode.ERR_WORKING_DAY_NOT_FOUND))));
    }

    @Override
    @Transactional
    public UUID save(CalendarFeignData calendarFeignData) {

        WorkingDay workingDay = workingDayMapper.toWorkingDay(calendarFeignData.getRequestWorkingDayDto());

        if (calendarFeignData.getIsHoliday()) {
            throw LogExceptionWrapper.logErrorException(new NotFoundException(ErrorCode.ERR_DAY_IS_HOLIDAY));
        }

        if (workingDayRepository.existsWorkingDayByMasterIdAndWorkingDate(workingDay.getMasterId(), workingDay.getWorkingDate())) {
            throw LogExceptionWrapper.logErrorException(new ExistsException(ErrorCode.ERR_WORKING_DAY_ALREADY_EXIST));
        }

        return workingDayRepository.save(workingDay).getId();
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseWorkingDayDTO findByMasterAndWorkingDate(UUID masterId, LocalDate workingDate) {

        return workingDayMapper
                .toWorkingDayDto(workingDayRepository.getWorkingDayByMasterIdAndWorkingDate(masterId, workingDate)
                        .orElseThrow(() -> LogExceptionWrapper.logErrorException(new NotFoundException(String.format(
                                ErrorCode.ERR_WORKING_DAY_NOT_FOUND_BY_ID_DATE.getMessage(), workingDate),
                                ErrorCode.ERR_WORKING_DAY_NOT_FOUND_BY_ID_DATE))));
    }
}

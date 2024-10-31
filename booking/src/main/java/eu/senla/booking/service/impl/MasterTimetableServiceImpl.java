package eu.senla.booking.service.impl;

import eu.senla.booking.dto.DateTimeRequest;
import eu.senla.booking.dto.GetMasterFreeTimesResponse;
import eu.senla.booking.entity.DayTime;
import eu.senla.booking.entity.MasterTimetable;
import eu.senla.booking.enums.ErrorCode;
import eu.senla.booking.exception.LogExceptionWrapper;
import eu.senla.booking.exception.NotFoundException;
import eu.senla.booking.mapper.MasterTimetableMapper;
import eu.senla.booking.repository.MasterTimetableRepository;
import eu.senla.booking.service.DayTimeService;
import eu.senla.booking.service.MasterTimetableService;
import eu.senla.booking.util.ExceptionMessageTemplateConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static eu.senla.booking.util.ExceptionMessageTemplateConstants.MASTER_TIMETABLE_WITH_ID_NOT_FOUND_MSG_TEMPLATE;

@Service
@RequiredArgsConstructor
public class MasterTimetableServiceImpl implements MasterTimetableService {

    private final MasterTimetableRepository masterTimeTableRepository;

    private final DayTimeService dayTimeService;

    private final MasterTimetableMapper masterTimeTableMapper;

    @Override
    @Transactional(readOnly = true)
    public MasterTimetable getMasterTimeTable(Long masterTimeTableId) {
        return masterTimeTableRepository.findById(masterTimeTableId).orElseThrow(() -> LogExceptionWrapper.logErrorException(
                new NotFoundException(String.format(MASTER_TIMETABLE_WITH_ID_NOT_FOUND_MSG_TEMPLATE, masterTimeTableId),
                        ErrorCode.ERR_OBJECT_NOT_FOUND)));
    }

    @Override
    @Transactional
    public void markDayAsWorkDay(UUID masterId, DateTimeRequest request) {
        MasterTimetable masterTimetable = masterTimeTableMapper.toEntity(masterId, request);
        DayTime dayTime = dayTimeService.getDayTime(request.dayTimeId());
        masterTimetable.setDayTime(dayTime);
        masterTimeTableRepository.save(masterTimetable);
    }

    @Override
    @Transactional(readOnly = true)
    public GetMasterFreeTimesResponse getMasterFreeTimes(UUID masterId) {
        List<MasterTimetable> list = masterTimeTableRepository.findAllByMasterIdAndIsAvailable(masterId, true);
        return masterTimeTableMapper.toResponse(list);
    }
}

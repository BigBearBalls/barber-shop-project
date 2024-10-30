package eu.senla.booking.service.impl;

import eu.senla.booking.dto.DayTimesResponse;
import eu.senla.booking.entity.DayTime;
import eu.senla.booking.enums.ErrorCode;
import eu.senla.booking.exception.DayTimeNotFoundException;
import eu.senla.booking.exception.LogExceptionWrapper;
import eu.senla.booking.mapper.DayTimeMapper;
import eu.senla.booking.repository.DayTimeRepository;
import eu.senla.booking.service.DayTimeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DayTimeServiceImpl implements DayTimeService {

    private final DayTimeRepository dayTimeRepository;

    private final DayTimeMapper dayTimeMapper;

    @Override
    @Transactional(readOnly = true)
    public DayTime getDayTime(Short dayTimeId) {
        return dayTimeRepository.findById(dayTimeId).orElseThrow(() -> LogExceptionWrapper.logErrorException(
                new DayTimeNotFoundException(String.format("DayTime with ID (%s) not found!", dayTimeId),
                        ErrorCode.ERR_OBJECT_NOT_FOUND)));
    }

    @Override
    @Transactional(readOnly = true)
    public DayTimesResponse getDayTimes() {
        List<DayTime> list = dayTimeRepository.findAll();
        return dayTimeMapper.toResponse(list);
    }
}

package eu.senla.booking.service.impl;

import eu.senla.booking.entity.DayTime;
import eu.senla.booking.enums.ErrorCode;
import eu.senla.booking.exception.DayTimeNotFoundException;
import eu.senla.booking.exception.LogExceptionWrapper;
import eu.senla.booking.repository.DayTimeRepository;
import eu.senla.booking.service.DayTimeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class DayTimeServiceImpl implements DayTimeService {

    private final DayTimeRepository dayTimeRepository;

    @Override
    public DayTime getDayTime(Short dayTimeId) {
        return dayTimeRepository.findById(dayTimeId).orElseThrow(() -> LogExceptionWrapper.logErrorException(
                new DayTimeNotFoundException(dayTimeId.toString(), ErrorCode.ERR_OBJECT_NOT_FOUND)));
    }
}

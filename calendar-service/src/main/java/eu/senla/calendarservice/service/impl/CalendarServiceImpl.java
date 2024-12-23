package eu.senla.calendarservice.service.impl;

import eu.senla.calendarservice.entity.DayOff;
import eu.senla.calendarservice.mapper.CalendarMapper;
import eu.senla.calendarservice.repository.CalendarRepository;
import eu.senla.calendarservice.service.CalendarService;
import eu.senla.common.enums.ErrorCode;
import eu.senla.httpconfiguration.exceptioncontroller.exception.ExistsException;
import eu.senla.httpconfiguration.exceptioncontroller.exception.InvalidValueException;
import eu.senla.httpconfiguration.exceptioncontroller.exception.LogExceptionWrapper;
import eu.senla.httpconfiguration.exceptioncontroller.exception.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CalendarServiceImpl implements CalendarService {

    private final CalendarRepository calendarRepository;
    private final CalendarMapper calendarMapper;

    @Transactional
    @Override
    public LocalDate setDayOff(LocalDate day) {
        if (day.isBefore(LocalDate.now())) {
            throw LogExceptionWrapper.logErrorException(new InvalidValueException(ErrorCode.ERR_DATE_CANNOT_BE_IN_PAST));
        }
        if (calendarRepository.existsByDate(day)) {
            throw LogExceptionWrapper.logErrorException(new ExistsException(String.format(
                    ErrorCode.ERR_ALREADY_DAY_OFF.getMessage(), day), ErrorCode.ERR_ALREADY_DAY_OFF));
        }
        DayOff calendarDayOff = calendarMapper.toCalendarDayOff(day);
        return calendarRepository.save(calendarDayOff).getDate();
    }

    @Transactional
    @Override
    public void cancelDayOff(LocalDate day) {
        if (!calendarRepository.existsByDate(day)) {
            throw LogExceptionWrapper.logErrorException(new NotFoundException(String.format(
                    ErrorCode.ERR_NOT_DAY_OFF.getMessage(), day), ErrorCode.ERR_NOT_DAY_OFF));
        }
        calendarRepository.deleteByDate(day);
    }

    @Transactional
    @Override
    public Boolean checkDay(LocalDate day) {
        return calendarRepository.existsByDate(day);
    }
}

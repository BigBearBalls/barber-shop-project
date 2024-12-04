package eu.senla.calendarservice.service.impl;

import eu.senla.calendarservice.entity.DayOff;
import eu.senla.calendarservice.exception.EmptyDateException;
import eu.senla.calendarservice.exception.InvalidDateException;
import eu.senla.calendarservice.mapper.CalendarMapper;
import eu.senla.calendarservice.repository.CalendarRepository;
import eu.senla.calendarservice.service.CalendarService;
import eu.senla.calendarservice.util.constants.ErrorConstants;
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
            throw new InvalidDateException(ErrorConstants.INVALID_DATE_ERROR_MESSAGE, ErrorConstants.INVALID_DATE_ERROR_CODE);
        }
        DayOff calendarDayOff = calendarMapper.toCalendarDayOff(day);
        return calendarRepository.save(calendarDayOff).getDate();
    }

    @Transactional
    @Override
    public void cancelDayOff(LocalDate day) {
        if (calendarRepository.existsByDate(day)) {
            calendarRepository.deleteByDate(day);
        } else {
            throw new EmptyDateException(ErrorConstants.EMPTY_DATE_ERROR_MESSAGE, ErrorConstants.EMPTY_DATE_ERROR_CODE);
        }
    }

    @Transactional
    @Override
    public Boolean checkDay(LocalDate day) {
        return calendarRepository.existsByDate(day);
    }
}

package eu.senla.calendarservice.service.impl;

import eu.senla.calendarservice.dto.IsHolidayDayResponse;
import eu.senla.calendarservice.entity.CalendarDayOff;
import eu.senla.calendarservice.exception.EmptyDateException;
import eu.senla.calendarservice.exception.InvalidDateException;
import eu.senla.calendarservice.mapper.CalendarMapper;
import eu.senla.calendarservice.repository.CalendarRepository;
import eu.senla.calendarservice.service.CalendarService;
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
            throw new InvalidDateException("The date cannot be earlier than today.");
        }
        CalendarDayOff calendarDayOff = calendarMapper.toCalendarDayOff(day);
        return calendarRepository.save(calendarDayOff).getDate();
    }

    @Transactional
    @Override
    public void setWorkingDay(LocalDate day) {
        if(calendarRepository.existsByDate(day)){
            calendarRepository.deleteByDate(day);
        } else {
            throw new EmptyDateException("This day is already a working day");
        }
    }

    @Transactional
    @Override
    public IsHolidayDayResponse isWorkingDay(LocalDate day) {
         return calendarMapper.toIsHolidayDayResponse(calendarRepository.existsByDate(day));
    }
}

package eu.senla.calendarservice.service;

import eu.senla.calendarservice.entity.DayOff;

import java.time.LocalDate;
import java.util.List;

public interface CalendarService {

    LocalDate setDayOff(LocalDate day);

    void cancelDayOff(LocalDate day);

    Boolean checkDay(LocalDate day);

    List<DayOff> getAllHolidays();
}

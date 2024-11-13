package eu.senla.calendarservice.service;

import eu.senla.calendarservice.dto.IsHolidayResponse;

import java.time.LocalDate;

public interface CalendarService {

    LocalDate setDayOff(LocalDate day);

    void setWorkingDay(LocalDate day);

    IsHolidayResponse isHolidayDay(LocalDate day);
}

package eu.senla.calendarservice.service;

import java.time.LocalDate;

public interface CalendarService {

    LocalDate setDayOff(LocalDate day);

    void setWorkingDay(LocalDate day);

    Boolean checkDay(LocalDate day);
}

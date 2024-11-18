package eu.senla.calendarservice.service;

import java.time.LocalDate;

public interface CalendarService {

    LocalDate setDayOff(LocalDate day);

    void cancelDayOff(LocalDate day);

    Boolean checkDay(LocalDate day);
}

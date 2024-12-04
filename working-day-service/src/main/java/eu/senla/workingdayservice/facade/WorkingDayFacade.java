package eu.senla.workingdayservice.facade;

import eu.senla.workingdayservice.dto.RequestWorkingDayDto;

import java.util.UUID;

public interface WorkingDayFacade {

    UUID collectDataFromCalendar(RequestWorkingDayDto requestWorkingDayDto);

}

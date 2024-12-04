package eu.senla.workingdayservice.facade;

import eu.senla.common.workingday.dto.request.RequestWorkingDayDTO;

import java.util.UUID;

public interface WorkingDayFacade {

    UUID collectDataFromCalendar(RequestWorkingDayDTO requestWorkingDayDto);

}

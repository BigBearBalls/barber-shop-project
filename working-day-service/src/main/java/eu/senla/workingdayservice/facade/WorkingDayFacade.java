package eu.senla.workingdayservice.facade;

import eu.senla.workingdayservice.data.req.CalendarFeignData;
import eu.senla.workingdayservice.dto.RequestWorkingDayDto;

import java.time.LocalDate;
import java.util.UUID;

public interface WorkingDayFacade {

    UUID collectDataFromCalendar(RequestWorkingDayDto requestWorkingDayDto);

}

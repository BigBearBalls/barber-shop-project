package eu.senla.workingdayservice.facade.impl;

import eu.senla.workingdayservice.client.CalendarClient;
import eu.senla.workingdayservice.data.req.CalendarFeignData;
import eu.senla.workingdayservice.dto.RequestWorkingDayDto;
import eu.senla.workingdayservice.facade.WorkingDayFacade;
import eu.senla.workingdayservice.service.WorkingDayService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WorkingDayFacadeImpl implements WorkingDayFacade {

    private final CalendarClient calendarClient;
    private final WorkingDayService workingDayService;

    @Override
    public UUID collectDataFromCalendar(RequestWorkingDayDto requestWorkingDayDto) {
        Boolean isHoliday = calendarClient.checkDay(requestWorkingDayDto.getWorkingDate());
        CalendarFeignData calendarFeignData = new CalendarFeignData(isHoliday, requestWorkingDayDto);
        return workingDayService.save(calendarFeignData);
    }


}

package eu.senla.workingdayservice.service;

import eu.senla.workingdayservice.data.req.CalendarFeignData;
import eu.senla.workingdayservice.dto.RequestWorkingDayDto;
import eu.senla.workingdayservice.dto.ResponseWorkingDayDto;

import java.time.LocalDate;
import java.util.UUID;

public interface WorkingDayService {

    ResponseWorkingDayDto findById(UUID id);

    UUID save(CalendarFeignData calendarFeignData);

    ResponseWorkingDayDto findByMasterAndWorkingDate(UUID master, LocalDate workingDate);
}

package eu.senla.workingdayservice.service;

import eu.senla.common.workingday.dto.request.CalendarFeignData;
import eu.senla.common.workingday.dto.response.ResponseWorkingDayDTO;

import java.time.LocalDate;
import java.util.UUID;

public interface WorkingDayService {

    ResponseWorkingDayDTO findById(UUID id);

    UUID save(CalendarFeignData calendarFeignData);

    ResponseWorkingDayDTO findByMasterAndWorkingDate(UUID master, LocalDate workingDate);
}

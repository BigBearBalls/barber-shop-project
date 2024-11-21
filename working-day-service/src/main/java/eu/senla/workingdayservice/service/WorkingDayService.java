package eu.senla.workingdayservice.service;

import eu.senla.workingdayservice.dto.RequestWorkingDayDto;
import eu.senla.workingdayservice.dto.ResponseWorkingDayDto;

import java.time.LocalDate;
import java.util.UUID;

public interface WorkingDayService {

    ResponseWorkingDayDto findById(UUID id);

    UUID save(RequestWorkingDayDto requestWorkingDayDto);

    ResponseWorkingDayDto findByMasterAndWorkingDate(UUID master, LocalDate workingDate);
}

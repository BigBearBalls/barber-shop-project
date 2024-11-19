package eu.senla.workingdayservice.service;

import eu.senla.workingdayservice.dto.WorkingDayDto;
import java.time.LocalDate;
import java.util.UUID;

public interface WorkingDayService {

    WorkingDayDto findById(UUID id);

    UUID save(WorkingDayDto workingDayDto);

    WorkingDayDto findByMasterAndWorkingDate(UUID master, LocalDate workingDate);
}

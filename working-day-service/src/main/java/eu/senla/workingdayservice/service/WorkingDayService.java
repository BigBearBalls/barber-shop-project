package eu.senla.workingdayservice.service;

import eu.senla.workingdayservice.dto.WorkingDayDto;
import java.time.LocalDate;
import java.util.UUID;

public interface WorkingDayService {

    WorkingDayDto findById(int id);

    Integer save(WorkingDayDto workingDayDto);

    WorkingDayDto findByMasterAndWorkingDate(UUID master, LocalDate workingDate);
}

package eu.senla.booking.service;

import eu.senla.booking.entity.WorkingDay;

import java.time.LocalDate;
import java.util.UUID;

public interface WorkingDayService {
    WorkingDay findWorkingDayByMasterAndWorkingDate(UUID master, LocalDate workingDate);
    WorkingDay findById(int id);
}

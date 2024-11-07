package eu.senla.booking.service.impl;

import eu.senla.booking.repository.WorkingDayRepository;
import eu.senla.booking.entity.WorkingDay;
import eu.senla.booking.service.WorkingDayService;
import eu.senla.booking.service.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

import static eu.senla.booking.dto.response.ErrorMessage.WORKING_DAY_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class WorkingDayServiceImpl implements WorkingDayService {

     private final WorkingDayRepository workingDayRepository;

     @Override
     public WorkingDay findWorkingDayByMasterAndWorkingDate(UUID master, LocalDate workingDate) {
          return workingDayRepository
                 .getWorkingDayByMasterAndWorkingDate(master, workingDate)
                 .orElseThrow(() -> new ResourceNotFoundException(WORKING_DAY_NOT_FOUND));

     }

     @Override
     public WorkingDay findById(int id) {
          return workingDayRepository.findById(id)
                  .orElseThrow(() -> new ResourceNotFoundException(WORKING_DAY_NOT_FOUND));
     }
}

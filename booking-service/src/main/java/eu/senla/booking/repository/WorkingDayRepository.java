package eu.senla.booking.repository;

import eu.senla.booking.entity.WorkingDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@RestController
public interface WorkingDayRepository extends JpaRepository<WorkingDay, Integer> {
    Optional<WorkingDay> getWorkingDayByMasterAndWorkingDate(UUID master, LocalDate workingDate);
}

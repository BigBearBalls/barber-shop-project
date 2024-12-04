package eu.senla.workingdayservice.repository;

import eu.senla.workingdayservice.entity.WorkingDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface WorkingDayRepository extends JpaRepository<WorkingDay, UUID> {

    Boolean existsWorkingDayByMasterIdAndWorkingDate(UUID masterId, LocalDate date);

    Optional<WorkingDay> getWorkingDayByMasterIdAndWorkingDate(UUID masterId, LocalDate workingDate);
}

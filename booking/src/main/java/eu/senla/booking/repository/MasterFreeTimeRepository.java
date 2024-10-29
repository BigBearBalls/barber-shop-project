package eu.senla.booking.repository;

import eu.senla.booking.entity.MasterFreeTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterFreeTimeRepository extends JpaRepository<MasterFreeTime, Long> {
}

package eu.senla.booking.repository;

import eu.senla.booking.entity.DayTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DayTimeRepository extends JpaRepository<DayTime, Short> {
}

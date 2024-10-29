package eu.senla.booking.repository;

import eu.senla.booking.entity.DayTime;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DayTimeRepository extends CrudRepository<DayTime, Short> {
}

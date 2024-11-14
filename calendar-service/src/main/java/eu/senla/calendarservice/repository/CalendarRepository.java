package eu.senla.calendarservice.repository;

import eu.senla.calendarservice.entity.DayOff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface CalendarRepository extends JpaRepository<DayOff, Long> {

    void deleteByDate(LocalDate date);

    Boolean existsByDate(LocalDate date);
}

package eu.senla.calendarservice.repository;

import eu.senla.calendarservice.entity.CalendarDayOff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface CalendarRepository extends JpaRepository<CalendarDayOff, Long> {

    void deleteByDate(LocalDate date);
    Boolean existsByDate(LocalDate date);
}

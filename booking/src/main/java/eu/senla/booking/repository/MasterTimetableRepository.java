package eu.senla.booking.repository;

import eu.senla.booking.entity.MasterTimetable;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface MasterTimetableRepository extends JpaRepository<MasterTimetable, Long> {

    @Query(value = "select t from MasterTimetable t where t.masterId=:masterId and t.isAvailable=:isAvailable " +
            "and t.date>:date and t.dayTime.time>:time")
    List<MasterTimetable> getAllAvailableMasterTimetables(UUID masterId, boolean isAvailable, LocalDate date, LocalTime time);
}

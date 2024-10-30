package eu.senla.booking.repository;

import eu.senla.booking.entity.MasterTimetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MasterTimetableRepository extends JpaRepository<MasterTimetable, Long> {

    /*@Query(value = "select t from MasterTimetable")
    @Lock(LockModeType.PESSIMISTIC_READ)*/
    List<MasterTimetable> findAllByMasterIdAndIsAvailable(UUID masterId, boolean isAvailable);
}

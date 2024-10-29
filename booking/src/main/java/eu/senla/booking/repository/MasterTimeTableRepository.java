package eu.senla.booking.repository;

import eu.senla.booking.entity.MasterTimeTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MasterTimeTableRepository extends JpaRepository<MasterTimeTable, Long> {

    List<MasterTimeTable> findAllByMasterIdAndIsAvailable(UUID masterId, boolean isAvailable);

}

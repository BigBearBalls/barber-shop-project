package eu.senla.booking.repository;

import eu.senla.booking.entity.TimeSlot;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlot, UUID> {

    List<TimeSlot> findAllByReservationStartGreaterThanEqualAndReservationEndLessThanEqual(LocalTime reservationStart, LocalTime reservationEnd);

    List<TimeSlot> findAllByIdNotIn(List<UUID> timeSlotIds);
}

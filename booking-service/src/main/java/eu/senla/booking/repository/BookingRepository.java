package eu.senla.booking.repository;

import eu.senla.booking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {
    List<Booking> findAllByWorkingDayId(UUID id);

    Optional<Booking> findById(UUID id);
}

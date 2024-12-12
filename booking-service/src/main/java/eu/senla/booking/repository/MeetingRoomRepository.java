package eu.senla.booking.repository;

import eu.senla.booking.entity.MeetingRoom;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingRoomRepository extends JpaRepository<MeetingRoom, UUID> {

    boolean existsById(UUID id);
}

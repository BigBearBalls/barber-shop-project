package eu.senla.booking.service;

import eu.senla.booking.entity.MeetingRoom;
import java.util.UUID;

public interface MeetingRoomService {

    MeetingRoom findMeetingRoomById(UUID id);

    boolean existsById(UUID id);
}

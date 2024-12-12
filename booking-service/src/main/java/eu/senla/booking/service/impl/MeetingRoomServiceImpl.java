package eu.senla.booking.service.impl;

import eu.senla.booking.entity.MeetingRoom;
import eu.senla.booking.repository.MeetingRoomRepository;
import eu.senla.booking.service.MeetingRoomService;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class MeetingRoomServiceImpl implements MeetingRoomService {

    private final MeetingRoomRepository meetingRoomRepository;

    @Override
    public MeetingRoom findMeetingRoomById(UUID id) {
        return meetingRoomRepository.findById(id).orElse(null);
        //TODO exception
    }

    @Override
    public boolean existsById(UUID id) {
        return meetingRoomRepository.existsById(id);
    }
}

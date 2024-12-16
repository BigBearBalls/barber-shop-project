package eu.senla.booking.service.impl;

import eu.senla.booking.entity.MeetingRoom;
import eu.senla.booking.repository.MeetingRoomRepository;
import eu.senla.booking.service.MeetingRoomService;
import eu.senla.common.enums.ErrorCode;
import eu.senla.common.exception.NotFoundException;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class MeetingRoomServiceImpl implements MeetingRoomService {

    private final MeetingRoomRepository meetingRoomRepository;

    @Override
    public MeetingRoom findMeetingRoomById(UUID id) {

        return meetingRoomRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(ErrorCode.ERR_MEETING_ROOM_NOT_FOUND.getMessage(), "id", id),
                                                         ErrorCode.ERR_MEETING_ROOM_NOT_FOUND));
    }

    @Override
    public boolean existsById(UUID id) {
        return meetingRoomRepository.existsById(id);
    }
}

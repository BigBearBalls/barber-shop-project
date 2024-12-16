package eu.senla.booking.data.mapper;

import eu.senla.booking.entity.MeetingRoom;
import eu.senla.common.booking.dto.response.MeetingRoomResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MeetingRoomMapper {

    MeetingRoomResponseDTO toMeetingRoomResponseDto(MeetingRoom meetingRoom);
}

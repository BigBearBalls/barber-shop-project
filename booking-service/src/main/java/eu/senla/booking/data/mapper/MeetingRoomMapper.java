package eu.senla.booking.data.mapper;

import eu.senla.booking.entity.MeetingRoom;
import eu.senla.booking.entity.MeetingRoomResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MeetingRoomMapper {

    MeetingRoomResponseDto toMeetingRoomResponseDto(MeetingRoom meetingRoom);
}

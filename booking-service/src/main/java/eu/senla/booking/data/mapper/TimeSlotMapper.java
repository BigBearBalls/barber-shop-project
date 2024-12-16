package eu.senla.booking.data.mapper;

import eu.senla.booking.entity.TimeSlot;
import eu.senla.booking.entity.TimeSlotResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TimeSlotMapper {

    TimeSlot toTimeSlot(TimeSlotResponseDto timeSlotResponseDto);

    TimeSlotResponseDto toTimeSlotResponseDto(TimeSlot timeSlot);
}

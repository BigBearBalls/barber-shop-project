package eu.senla.booking.data.mapper;

import eu.senla.booking.entity.Booking;
import eu.senla.booking.entity.TimeSlot;
import eu.senla.common.booking.dto.response.TimeSlotResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TimeSlotMapper {

//    TimeSlot toTimeSlot(TimeSlotResponseDTO timeSlotResponseDto);

    TimeSlotResponseDTO toTimeSlotResponseDto(TimeSlot timeSlot);
//    TimeSlotResponseDTO toTimeSlotResponseDto(Booking booking);
}

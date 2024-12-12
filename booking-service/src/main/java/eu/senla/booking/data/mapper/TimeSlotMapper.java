package eu.senla.booking.data.mapper;

import eu.senla.booking.entity.TimeSlot;
import eu.senla.booking.entity.TimeSlotDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TimeSlotMapper {

    @Mapping(source = "reservationStart", target = "reservationStart")
    @Mapping(source = "reservationEnd", target = "reservationEnd")
    TimeSlot toTimeSlot(TimeSlotDto timeSlotDto);

    TimeSlotDto toTimeSlotDto(TimeSlot timeSlot);
}

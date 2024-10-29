package eu.senla.booking.mapper;

import eu.senla.booking.dto.BookTimeRequest;
import eu.senla.booking.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookingMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "masterTimeTable", ignore = true)
    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "procedureId", source = "procedureId")
    @Mapping(target = "status", constant = "OPEN")
    Booking toEntity(BookTimeRequest request);

}

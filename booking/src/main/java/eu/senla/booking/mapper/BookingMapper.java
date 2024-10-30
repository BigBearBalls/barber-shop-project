package eu.senla.booking.mapper;

import eu.senla.booking.dto.BookTimeRequest;
import eu.senla.booking.dto.BookingRecord;
import eu.senla.booking.dto.ProcedureDTO;
import eu.senla.booking.dto.UserDTO;
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
    @Mapping(target = "createAt", ignore = true)
    @Mapping(target = "updateAt", ignore = true)
    Booking toEntity(BookTimeRequest request);

    @Mapping(target = "bookingId", source = "booking.id")
    @Mapping(target = "master", source = "userDTO")
    @Mapping(target = "date", source = "booking.masterTimeTable.date")
    @Mapping(target = "time", source = "booking.masterTimeTable.dayTime.time")
    @Mapping(target = "procedure", source = "procedureDTO")
    @Mapping(target = "status", source = "booking.status")
    BookingRecord toDTO(Booking booking, UserDTO userDTO, ProcedureDTO procedureDTO);
}

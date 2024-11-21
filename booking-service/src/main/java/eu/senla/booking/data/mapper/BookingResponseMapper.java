package eu.senla.booking.data.mapper;

import eu.senla.booking.data.ProcedureDTO;
import eu.senla.booking.data.ResponseWorkingDayDto;
import eu.senla.booking.data.UserDTO;
import eu.senla.booking.data.response.BookingResponseDTO;
import eu.senla.booking.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookingResponseMapper {

    @Mapping(source = "client.firstName", target = "clientFirstName")
    @Mapping(source = "client.lastName", target = "clientLastName")
    @Mapping(source = "master.firstName", target = "masterFirstname")
    @Mapping(source = "master.lastName", target = "masterLastName")
    @Mapping(source = "procedure.procedureName", target = "procedure")
    @Mapping(source = "procedure.procedurePrice", target = "price")
    @Mapping(source = "procedure.duration", target = "duration")
    @Mapping(source = "workingDay.workingDate", target = "date")
    @Mapping(source = "booking.reservationStart", target = "time")
    BookingResponseDTO toBookingResponseDTO(UserDTO client, UserDTO master,
                                            ProcedureDTO procedure, Booking booking, ResponseWorkingDayDto workingDay);

}

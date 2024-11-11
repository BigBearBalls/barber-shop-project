package eu.senla.booking.data.mapper;

import eu.senla.booking.data.ProcedureDTO;
import eu.senla.booking.data.UserDTO;
import eu.senla.booking.data.response.BookingResponseDTO;
import eu.senla.booking.entity.Booking;
import eu.senla.booking.entity.WorkingDay;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookingResponseMapper {

    @Mapping(source = "client.firstname", target = "clientFirstName")
    @Mapping(source = "client.lastname", target = "clientLastName")
    @Mapping(source = "master.firstname", target = "masterFirstname")
    @Mapping(source = "master.lastname", target = "masterLastName")
    @Mapping(source = "procedure.name", target = "procedure")
    @Mapping(source = "procedure.price", target = "price")
    @Mapping(source = "procedure.duration", target = "duration")
    @Mapping(source = "workingDay.workingDate", target = "date")
    @Mapping(source = "booking.reservationStart", target = "time")
    BookingResponseDTO toBookingResponseDTO(UserDTO client, UserDTO master,
                                            ProcedureDTO procedure, Booking booking, WorkingDay workingDay);

}

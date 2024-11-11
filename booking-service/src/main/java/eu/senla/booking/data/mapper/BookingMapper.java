package eu.senla.booking.data.mapper;

import eu.senla.booking.data.ProcedureDTO;
import eu.senla.booking.data.request.BookingRequestDTO;
import eu.senla.booking.entity.Booking;
import eu.senla.booking.entity.WorkingDay;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.time.LocalTime;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookingMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "booking.clientId", target = "clientId")
    @Mapping(source = "procedure.id", target = "procedureId")
    @Mapping(source = "workingDay.id", target = "workingDayId")
    @Mapping(source = "booking.reservationStart", target = "reservationStart")
    @Mapping(source = "reservationEnd", target = "reservationEnd")
    Booking toBooking(BookingRequestDTO booking, ProcedureDTO procedure, WorkingDay workingDay, LocalTime reservationEnd);
}

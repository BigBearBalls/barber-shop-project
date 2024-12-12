package eu.senla.booking.data.mapper;

import eu.senla.booking.entity.Booking;
import eu.senla.booking.entity.BookingRequestDto;
import eu.senla.booking.entity.BookingResponseDto;
import eu.senla.booking.entity.TimeSlot;
//import eu.senla.common.booking.dto.request.BookingRequestDTO;
import eu.senla.common.booking.dto.response.ResponseWorkingDayDTO;
import java.util.Optional;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = TimeSlotMapper.class)
public interface BookingMapper {

    default Booking toBooking(final BookingRequestDto bookingRequestDto) {
        Booking booking = new Booking();

        TimeSlot timeSlot = new TimeSlot();
        Optional.ofNullable(bookingRequestDto.getReservationStart()).ifPresent(timeSlot::setReservationStart);
        Optional.ofNullable(bookingRequestDto.getReservationEnd()).ifPresent(timeSlot::setReservationEnd);

        booking.getTimeSlots().add(timeSlot);
        Optional.ofNullable(bookingRequestDto.getMeetingRoomId()).ifPresent(booking::setMeetingRoomId);
        Optional.ofNullable(bookingRequestDto.getReservationDate()).ifPresent(booking::setReservationDate);

        return booking;
    }

    BookingResponseDto toBookingResponseDto(final Booking booking);

//    @Mapping(target = "id", ignore = true)
//    @Mapping(source = "booking.clientId", target = "clientId")
//    @Mapping(source = "procedure.id", target = "procedureId")
//    @Mapping(source = "workingDay.id", target = "workingDayId")
//    @Mapping(source = "booking.reservationStart", target = "reservationStart")
//    @Mapping(source = "reservationEnd", target = "reservationEnd")
//    Booking toBooking(BookingRequestDTO booking, ProcedureDTO procedure, ResponseWorkingDayDTO workingDay, LocalTime reservationEnd);
}

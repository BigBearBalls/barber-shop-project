package eu.senla.booking.data.mapper;

import eu.senla.booking.entity.Booking;
import eu.senla.booking.entity.BookingRequestDto;
import eu.senla.booking.entity.BookingResponseDto;
import eu.senla.booking.entity.MeetingRoom;
import eu.senla.booking.entity.TimeSlot;
import java.util.Optional;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {TimeSlotMapper.class, MeetingRoomMapper.class})
public interface BookingMapper {

    default Booking toBooking(final BookingRequestDto bookingRequestDto) {
        Booking booking = new Booking();
        TimeSlot timeSlot = new TimeSlot();

        Optional.ofNullable(bookingRequestDto.getBookingStart()).ifPresent(timeSlot::setReservationStart);
        Optional.ofNullable(bookingRequestDto.getBookingEnd()).ifPresent(timeSlot::setReservationEnd);

        booking.getTimeSlots().add(timeSlot);
        MeetingRoom meetingRoom = new MeetingRoom();

        Optional.ofNullable(bookingRequestDto.getMeetingRoomId()).ifPresent(meetingRoom::setId);

        booking.setMeetingRoom(meetingRoom);

        Optional.ofNullable(bookingRequestDto.getBookingDate()).ifPresent(booking::setBookingDate);

        return booking;
    }

    BookingResponseDto toBookingResponseDto(final Booking booking);
}

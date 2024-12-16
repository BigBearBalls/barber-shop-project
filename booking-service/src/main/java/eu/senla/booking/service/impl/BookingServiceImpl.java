package eu.senla.booking.service.impl;

import eu.senla.booking.data.mapper.BookingMapper;
import eu.senla.booking.data.mapper.TimeSlotMapper;
import eu.senla.booking.entity.Booking;
import eu.senla.common.booking.dto.response.BookingResponseDTO;
import eu.senla.booking.entity.TimeSlot;
import eu.senla.common.booking.dto.response.TimeSlotResponseDTO;
import eu.senla.booking.repository.BookingRepository;
import eu.senla.booking.repository.TimeSlotRepository;
import eu.senla.booking.service.BookingService;
import eu.senla.booking.service.MeetingRoomService;
import eu.senla.booking.service.TimeSlotService;
import eu.senla.booking.service.kafka.BookingKafkaProducer;
import eu.senla.common.booking.dto.response.IdResponseDTO;
import eu.senla.common.enums.ErrorCode;
import eu.senla.common.exception.ExistsException;
import eu.senla.common.exception.InvalidValueException;
import eu.senla.common.exception.LogExceptionWrapper;
import eu.senla.common.exception.NotFoundException;
import java.time.LocalDate;

import eu.senla.common.kafka.dto.KafkaMailDto;
import eu.senla.common.kafka.dto.MailType;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    private final TimeSlotRepository timeSlotRepository;
    private final TimeSlotMapper timeSlotMapper;
    private final TimeSlotService timeSlotService;
    private final BookingMapper bookingMapper;
    private final MeetingRoomService meetingRoomService;
    private final BookingKafkaProducer bookingKafkaProducer;

    @Override
    public BookingResponseDTO findBookingById(UUID id) {

        Booking booking = bookingRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(ErrorCode.ERR_BOOKING_NOT_FOUND.getMessage(), id),
                        ErrorCode.ERR_BOOKING_NOT_FOUND));

        return bookingMapper.toBookingResponseDto(booking);
    }

    @Override
    public IdResponseDTO saveBooking(Booking booking) {

        if (!meetingRoomService.existsById(booking.getMeetingRoom().getId())) {
            throw LogExceptionWrapper.logErrorException(new NotFoundException(String.format(ErrorCode.ERR_MEETING_ROOM_NOT_FOUND.getMessage(),
                    "id", booking.getMeetingRoom().getId()), ErrorCode.ERR_MEETING_ROOM_NOT_FOUND));
        }

        TimeSlot timeSlot = booking
                .getTimeSlots()
                .stream()
                .findFirst()
                .get();

        if (booking.getBookingDate().isBefore(LocalDate.now())) {
            throw LogExceptionWrapper.logErrorException(new InvalidValueException(ErrorCode.ERR_BOOKING_DATE_CANNOT_BE_IN_PAST));
        }

        if (timeSlot.getReservationEnd().equals(timeSlot.getReservationStart())) {
            throw LogExceptionWrapper.logErrorException(new InvalidValueException(ErrorCode.ERR_START_TIME_CANNOT_BE_EQUAL_END_TIME));
        } else if ((timeSlot.getReservationStart().isAfter(timeSlot.getReservationEnd()))) {
            throw LogExceptionWrapper.logErrorException(new InvalidValueException(ErrorCode.ERR_START_TIME_CANNOT_BE_AFTER_END_TIME));
        }

        List<TimeSlot> timeSlotsForBooking = timeSlotService.findTimeSlotsForBooking(timeSlot.getReservationStart(),
                timeSlot.getReservationEnd());

        List<TimeSlot> availableTimeSlots = findAvailableTimeSlots(booking.getMeetingRoom().getId(), booking.getBookingDate());

        timeSlotsForBooking.forEach(slotForBooking -> {
            if (!availableTimeSlots.contains(slotForBooking)) {
                throw LogExceptionWrapper.logErrorException(new ExistsException(ErrorCode.ERR_TIME_SLOT_ALREADY_BOOKED));
            }
        });

        booking.setTimeSlots(timeSlotsForBooking);
        booking.setUserId(UUID.fromString("f2d4e0a1-16b3-4d9e-88d9-94a9cc93fe94")); //TODO
        IdResponseDTO idResponseDTO = new IdResponseDTO(bookingRepository.save(booking).getId());

        bookingKafkaProducer.sendUserRegistrationEvent("user-registration",
                new KafkaMailDto(MailType.BOOKING_MAIL, "evgturin@gmail.com", "NiHao" ,"You have successfully booked"));

        return idResponseDTO;
    }

    @Override
    public void delete(UUID id) {
        Booking booking = bookingRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(ErrorCode.ERR_BOOKING_NOT_FOUND.getMessage(), id),
                                                         ErrorCode.ERR_BOOKING_NOT_FOUND));

        bookingRepository.delete(booking);
    }

    @Override
    public List<TimeSlotResponseDTO> findAvailableTimeSlotsDto(UUID meetingRoomId, LocalDate bookingDate) {

        if (!meetingRoomService.existsById(meetingRoomId)) {
            throw LogExceptionWrapper.logErrorException(new NotFoundException(String.format(ErrorCode.ERR_MEETING_ROOM_NOT_FOUND.getMessage(),
                    "id", meetingRoomId), ErrorCode.ERR_MEETING_ROOM_NOT_FOUND));
        }

        List<TimeSlot> availableTimeSlots = findAvailableTimeSlots(meetingRoomId, bookingDate);

        return availableTimeSlots
                .stream()
                .map(timeSlotMapper::toTimeSlotResponseDto)
                .toList();
    }

    private List<TimeSlot> findAvailableTimeSlots(UUID meetingRoomId, LocalDate bookingDate) {

        List<Booking> bookings = bookingRepository.findAllByReservationDateAndMeetingRoomId(bookingDate,
                meetingRoomId);
        List<UUID> bookedTimeSlotIds = bookings
                .stream()
                .map(Booking::getTimeSlots)
                .flatMap(List::stream)
                .map(TimeSlot::getId)
                .toList();
        if (bookedTimeSlotIds.isEmpty()) {
            return timeSlotRepository.findAll();
        } else {
            return timeSlotRepository.findAllByIdNotIn(bookedTimeSlotIds);
        }
    }
}

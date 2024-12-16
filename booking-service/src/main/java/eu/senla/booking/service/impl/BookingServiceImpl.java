package eu.senla.booking.service.impl;

import eu.senla.booking.data.mapper.BookingMapper;
import eu.senla.booking.data.mapper.TimeSlotMapper;
import eu.senla.booking.entity.Booking;
import eu.senla.booking.entity.BookingResponseDto;
import eu.senla.booking.entity.TimeSlot;
import eu.senla.booking.entity.TimeSlotDto;
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
import java.time.LocalDate;

import eu.senla.common.kafka.dto.KafkaMailDto;
import eu.senla.common.kafka.dto.MailType;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
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
    public BookingResponseDto findBookingById(UUID bookingId) {

        return bookingMapper.toBookingResponseDto(bookingRepository.findById(bookingId).orElseThrow());
        //todo exception
    }

    @Override
    public IdResponseDTO saveBooking(Booking booking) {

//        MeetingRoom meetingRoom = meetingRoomService.findMeetingRoomById(booking.getMeetingRoomId()); //TODO check if exist
//        System.out.println(meetingRoom.getId());
//        System.out.println(meetingRoom.getNumber());

        if (meetingRoomService.existsById(booking.getMeetingRoomId())) {
            throw LogExceptionWrapper.logErrorException(new ExistsException(String.format(
                    ErrorCode.ERR_MEETING_ROOM_NOT_FOUND.getMessage(), booking.getMeetingRoomId()), ErrorCode.ERR_MEETING_ROOM_NOT_FOUND));
        }

        TimeSlot timeSlot = booking
                .getTimeSlots()
                .stream()
                .findFirst()
                .get();

        if (timeSlot.getReservationEnd().isBefore(timeSlot.getReservationStart())) {
            throw LogExceptionWrapper.logErrorException(new InvalidValueException(ErrorCode.ERR_TIME_CANNOT_BE_IN_PAST));
        }

        List<TimeSlot> timeSlotsForBooking = timeSlotService.findTimeSlotsForBooking(timeSlot.getReservationStart(),
                timeSlot.getReservationEnd());

        List<TimeSlot> availableTimeSlots = findAvailableTimeSlots(booking.getMeetingRoomId(), booking.getReservationDate());

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
        //TODO exception
        Booking booking = bookingRepository.findById(id).orElseThrow();

        bookingRepository.delete(booking);
    }

    @Override
    public List<TimeSlotDto> findAvailableTimeSlotsDto(UUID meetingRoomId, LocalDate bookingDate) {

        List<TimeSlot> availableTimeSlots = findAvailableTimeSlots(meetingRoomId, bookingDate);

        return availableTimeSlots
                .stream()
                .map(timeSlotMapper::toTimeSlotDto)
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

    @Override
    public void add() {
        System.out.println("addingTimeSlots");

        TimeSlot timeSlot1 = new TimeSlot();
        timeSlot1.setId(UUID.fromString("f2d4e0a1-16b3-4d9e-88d9-94a9cc93fe95"));
        timeSlot1.setReservationStart(LocalTime.parse("11:00"));
        timeSlot1.setReservationEnd(LocalTime.parse("11:30"));

        TimeSlot timeSlot2 = new TimeSlot();
        timeSlot2.setId(UUID.fromString("f2d4e0a1-16b3-4d9e-88d9-94a9cc93fe94"));
        timeSlot2.setReservationStart(LocalTime.parse("11:30"));
        timeSlot2.setReservationEnd(LocalTime.parse("12:00"));

        TimeSlot timeSlot3 = new TimeSlot();
        timeSlot3.setId(UUID.fromString("f2d4e0a1-16b3-4d9e-88d9-94a9cc93fe93"));
        timeSlot3.setReservationStart(LocalTime.parse("12:00"));
        timeSlot3.setReservationEnd(LocalTime.parse("12:30"));

        TimeSlot timeSlot4 = new TimeSlot();
        timeSlot4.setId(UUID.fromString("f2d4e0a1-16b3-4d9e-88d9-94a9cc93fe92"));
        timeSlot4.setReservationStart(LocalTime.parse("12:30"));
        timeSlot4.setReservationEnd(LocalTime.parse("13:00"));

        TimeSlot timeSlot5 = new TimeSlot();
        timeSlot5.setId(UUID.fromString("f2d4e0a1-16b3-4d9e-88d9-94a9cc93fe91"));
        timeSlot5.setReservationStart(LocalTime.parse("13:00"));
        timeSlot5.setReservationEnd(LocalTime.parse("13:30"));

        TimeSlot timeSlot6 = new TimeSlot();
        timeSlot6.setId(UUID.fromString("f2d4e0a1-16b3-4d9e-88d9-94a9cc93fe90"));
        timeSlot6.setReservationStart(LocalTime.parse("13:30"));
        timeSlot6.setReservationEnd(LocalTime.parse("14:00"));

        TimeSlot timeSlot7 = new TimeSlot();
        timeSlot7.setId(UUID.fromString("f2d4e0a1-16b3-4d9e-88d9-94a9cc93fe89"));
        timeSlot7.setReservationStart(LocalTime.parse("14:00"));
        timeSlot7.setReservationEnd(LocalTime.parse("14:30"));

        TimeSlot timeSlot8 = new TimeSlot();
        timeSlot8.setId(UUID.fromString("f2d4e0a1-16b3-4d9e-88d9-94a9cc93fe88"));
        timeSlot8.setReservationStart(LocalTime.parse("14:30"));
        timeSlot8.setReservationEnd(LocalTime.parse("15:00"));

        List<TimeSlot> timeSlots = List.of(timeSlot1, timeSlot2, timeSlot3, timeSlot4, timeSlot5, timeSlot6, timeSlot7, timeSlot8);

        timeSlotRepository.saveAll(timeSlots);
    }

//    @Transactional
//    @Override
//    public IdResponseDTO saveBooking(AggregatedBooking aggregatedBookingData) {
//
//        ResponseWorkingDayDTO workingMasterDay = aggregatedBookingData.getWorkingMasterDay();
//        ProcedureDTO procedure = aggregatedBookingData.getProcedure();
//
//        checkFreeTime(workingMasterDay, procedure.getDuration(),
//                aggregatedBookingData.getBookingRequest().getReservationStart());
//
//        Booking booking = bookingMapper.toBooking(aggregatedBookingData.getBookingRequest(), procedure,
//                workingMasterDay, aggregatedBookingData.getBookingRequest().getReservationStart().plusMinutes(procedure.getDuration()));
//        bookingRepository.save(booking);
//        log.debug("Booking with id: ${} has been created", booking.getId());
//        return new IdResponseDTO(booking.getId());
//    }
//
//    @Transactional
//    @Override
//    public Booking findBookingById(UUID id) {
//        return bookingRepository.findById(id)
//                .orElseThrow(() -> LogExceptionWrapper.logErrorException(new NotFoundException(String.format(
//                        ErrorCode.ERR_BOOKING_NOT_FOUND.getMessage(), id), ErrorCode.ERR_BOOKING_NOT_FOUND)));
//    }
//
//    private void checkFreeTime(ResponseWorkingDayDTO workingDay,
//                               Integer duration,
//                               LocalTime desiredStartTime) {
//
//        List<Booking> masterBookingsPerDay = bookingRepository
//                .findAllByWorkingDayId(workingDay.getId());
//
//        LocalTime workTimeStart = workingDay.getWorkStart();
//        LocalTime workTimeEnd = workingDay.getWorkEnd();
//        LocalTime desiredEndTime = desiredStartTime.plusMinutes(duration);
//
//        if (desiredStartTime.isBefore(workTimeStart) || desiredEndTime.isAfter(workTimeEnd)) {
//            throw LogExceptionWrapper.logErrorException(new MasterNotWorkException(ErrorCode.ERR_MASTER_NOT_WORKING));
//        }
//
//        for (Booking booking : masterBookingsPerDay) {
//            LocalTime bookingTimeStart = booking.getReservationStart();
//            LocalTime bookingTimeEnd = booking.getReservationEnd();
//
//            if (desiredStartTime.isBefore(bookingTimeEnd) && desiredEndTime.isAfter(bookingTimeStart)) {
//                throw LogExceptionWrapper.logErrorException(new ExistsException(ErrorCode.ERR_TIME_ALREADY_BOOKED));
//            }
//        }
//
//    }
}

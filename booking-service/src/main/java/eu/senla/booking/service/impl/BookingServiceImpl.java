package eu.senla.booking.service.impl;

import eu.senla.booking.client.DepartmentClient;
import eu.senla.booking.client.AuthClient;
import eu.senla.booking.client.UserClient;
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
import eu.senla.common.department.dto.response.DepartmentUserDTO;
import eu.senla.common.enums.ErrorCode;
import eu.senla.common.exception.ExistsException;
import eu.senla.common.exception.InvalidValueException;
import eu.senla.common.exception.LogExceptionWrapper;
import eu.senla.common.exception.NotFoundException;

import java.time.LocalDate;

import eu.senla.common.kafka.dto.KafkaMailDto;
import eu.senla.common.kafka.dto.MailType;

import eu.senla.httpconfiguration.security.holder.UserIdHolder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import org.springframework.transaction.annotation.Transactional;

import static eu.senla.common.enums.DepartmentRole.TEAM_LEADER;

@Slf4j
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
    private final DepartmentClient departmentClient;
    private final AuthClient authClient;
    private final UserClient userClient;

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

        log.info(booking.toString() + "------------------------");


//        MeetingRoom meetingRoom = meetingRoomService.findMeetingRoomById(booking.getMeetingRoomId()); //TODO check if exist
//        System.out.println(meetingRoom.getId());
//        System.out.println(meetingRoom.getNumber());

//        if (meetingRoomService.existsById(booking.getMeetingRoom().getId())) {
//            throw LogExceptionWrapper.logErrorException(new ExistsException(String.format(
//                    ErrorCode.ERR_MEETING_ROOM_NOT_FOUND.getMessage(), booking.getMeetingRoom().getId()), ErrorCode.ERR_MEETING_ROOM_NOT_FOUND));
//        }

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

        List<TimeSlot> availableTimeSlots = findAvailableTimeSlots(booking.getMeetingRoom().getId(), booking.getBookingDate());

        timeSlotsForBooking.forEach(slotForBooking -> {
            if (!availableTimeSlots.contains(slotForBooking)) {
                throw LogExceptionWrapper.logErrorException(new ExistsException(ErrorCode.ERR_TIME_SLOT_ALREADY_BOOKED));
            }
        });

        booking.setTimeSlots(timeSlotsForBooking);
        booking.setUserId(UUID.fromString("f2d4e0a1-16b3-4d9e-88d9-94a9cc93fe94")); //TODO


        DepartmentUserDTO department = departmentClient.getUser();
        IdResponseDTO idResponseDTO = new IdResponseDTO(bookingRepository.save(booking).getId());

        if (department.getRole().equals(TEAM_LEADER)) {
            booking.setStatus("APPROVED");
            String mail = authClient.getUserById(UserIdHolder.getUserId()).getEmail();
            bookingKafkaProducer.sendUserRegistrationEvent("user-registration",
                    new KafkaMailDto(MailType.BOOKING_MAIL, mail, "PLAHCTOH-BOOKING", "You have successfully booked"));
        } else {
            booking.setStatus("PENDING");
            approveBookingRequest(department, booking);
        }

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
    public void declineBooking(UUID bookingId) {
        bookingRepository.deleteById(bookingId);
    }

    @Override
    public void approveBooking(UUID bookingId) {
        Booking booking = bookingRepository.getBookingById(bookingId);
        booking.setStatus("APPROVED");
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

        List<Booking> bookings = bookingRepository.findAllByBookingDateAndMeetingRoomId(bookingDate,
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

    private void approveBookingRequest(DepartmentUserDTO department, Booking booking) {

        UUID teamLeadId = department.getTeamLeader().getId();
        String leadMail = authClient.getUserById(teamLeadId).getEmail();

        String userFirstName = userClient.getUser().getFirstName();
        String userLastName = userClient.getUser().getLastName();
        Integer meetRoomNumber = meetingRoomService.findMeetingRoomById(booking.getMeetingRoom().getId()).getNumber();


        StringBuilder mailMessageBuilder = new StringBuilder();
        String approveUrl = "http://localhost:7080/api/v1/bookings/approve?bookingId=" + booking.getId();
        String declineUrl = "http://localhost:7080/api/v1/bookings/decline?bookingId=" + booking.getId();

        mailMessageBuilder.append(userFirstName)
                .append(" ")
                .append(userLastName)
                .append(", wants to book a meeting room №")
                .append(meetRoomNumber)
                .append("<br><br>")
                .append("date: ")
                .append(booking.getBookingDate())
                .append("<br><br>")
//                .append("from: ")
////                .append(booking.getTimeSlots().)
//                .append("to: ")
//                .append(meetRoomNumber)
                .append(". Please choose one of the following options:\n\n")
                .append("<br><br>")
                .append("<a href='").append(approveUrl).append("' style='display: inline-block; padding: 12px 24px; color: white; background-color: #a8d5ba; text-decoration: none; border-radius: 5px; margin-right: 10px; box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.1);'>Approve</a>")
                .append("<a href='").append(declineUrl).append("' style='display: inline-block; padding: 12px 24px; color: white; background-color: #f7a8a8; text-decoration: none; border-radius: 5px; box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.1);'>Decline</a>");
        ;

        bookingKafkaProducer.sendUserRegistrationEvent("user-registration",
                new KafkaMailDto(MailType.APPROVE_BOOKING_MAIL,
                        leadMail, "Booking Approve", mailMessageBuilder.toString()));
    }

}

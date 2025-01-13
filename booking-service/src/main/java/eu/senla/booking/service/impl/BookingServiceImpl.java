package eu.senla.booking.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.booking.client.AuthClient;
import eu.senla.booking.client.DepartmentClient;
import eu.senla.booking.client.UserClient;
import eu.senla.booking.data.mapper.BookingMapper;
import eu.senla.booking.data.mapper.TimeSlotMapper;
import eu.senla.booking.entity.BookedTimeSlotDto;
import eu.senla.booking.entity.Booking;
import eu.senla.booking.entity.TimeSlot;
import eu.senla.booking.repository.BookingRepository;
import eu.senla.booking.repository.TimeSlotRepository;
import eu.senla.booking.service.BookingService;
import eu.senla.booking.service.MeetingRoomService;
import eu.senla.booking.service.TimeSlotService;
import eu.senla.booking.service.kafka.BookingKafkaProducer;
import eu.senla.common.booking.constant.KafkaConstants;
import eu.senla.common.booking.constant.MailConstants;
import eu.senla.common.booking.dto.request.ChangeBookingStatusDTO;
import eu.senla.common.booking.dto.request.TimeSlotInformationDto;
import eu.senla.common.booking.dto.request.UserTeamLeadDto;
import eu.senla.common.booking.dto.response.BookingResponseDTO;
import eu.senla.common.booking.dto.response.IdResponseDTO;
import eu.senla.common.booking.dto.response.TimeSlotResponseDTO;
import eu.senla.common.booking.enums.BookingStatus;
import eu.senla.common.department.dto.response.DepartmentUserDTO;
import eu.senla.common.dto.UserDataDTO;
import eu.senla.common.enums.ErrorCode;
import eu.senla.common.exception.ExistsException;
import eu.senla.common.exception.InvalidValueException;
import eu.senla.common.exception.LogExceptionWrapper;
import eu.senla.common.exception.NotFoundException;
import eu.senla.common.kafka.dto.KafkaMailDto;
import eu.senla.common.kafka.dto.MailType;
import eu.senla.httpconfiguration.security.holder.UserHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static eu.senla.common.enums.DepartmentRole.TEAM_LEADER;

@Slf4j
@Service
@RequiredArgsConstructor
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

    private final ObjectMapper objectMapper;

    @Value("${spring.application.domain}")
    private String domain;

    @Override
    public BookingResponseDTO findBookingById(UUID id) {

        Booking booking = bookingRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(ErrorCode.ERR_BOOKING_NOT_FOUND.getMessage(), id),
                        ErrorCode.ERR_BOOKING_NOT_FOUND));

        return bookingMapper.toBookingResponseDto(booking);
    }

    @Override
    public IdResponseDTO saveBooking(Booking booking) throws JsonProcessingException {

        log.info(booking.toString() + "------------------------");


//        MeetingRoom meetingRoom = meetingRoomService.findMeetingRoomById(booking.getMeetingRoomId()); //TODO check if exist
//        System.out.println(meetingRoom.getId());
//        System.out.println(meetingRoom.getNumber());
//
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
        booking.setUserId(UserHolder.getUser().getId());


        DepartmentUserDTO department = departmentClient.getUser();
        IdResponseDTO idResponseDTO = new IdResponseDTO(bookingRepository.save(booking).getId());

        String mail = UserHolder.getUser().getEmail();
        if (department.getRole().equals(TEAM_LEADER)) {
            booking.setStatus(BookingStatus.APPROVED);
            bookingKafkaProducer.sendMailSendEvent(KafkaConstants.MAIL_SENDER_TOPIC_NAME,
                    new KafkaMailDto(MailType.BOOKING_MAIL, mail, MailConstants.PLAHCTOH_BOOKING_MAIL_SUBJECT,
                            MailConstants.BOOKED_SUCCESSFULLY_MAIL_MESSAGE));
        } else {
            booking.setStatus(BookingStatus.PENDING);
            approveBookingRequest(department, booking);
            bookingKafkaProducer.sendMailSendEvent(KafkaConstants.MAIL_SENDER_TOPIC_NAME,
                    new KafkaMailDto(MailType.BOOKING_MAIL, mail, MailConstants.PLAHCTOH_BOOKING_MAIL_SUBJECT,
                            MailConstants.BOOKING_REQUEST_CREATED_AWAIT_APROVE_MAIL_MESSAGE));
        }

        return idResponseDTO;
    }

    @Override
    public void delete(UUID id) {
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> LogExceptionWrapper.logErrorException(
                new NotFoundException(String.format(ErrorCode.ERR_BOOKING_NOT_FOUND.getMessage(), id),
                        ErrorCode.ERR_BOOKING_NOT_FOUND)));
        booking.setStatus(BookingStatus.CANCELLED);
    }

    @Override
    public void changeBookingStatus(ChangeBookingStatusDTO dto) {
        Booking booking = bookingRepository.findById(dto.getBookingId()).orElseThrow(() -> LogExceptionWrapper
                .logErrorException(new NotFoundException(String.format(ErrorCode.ERR_BOOKING_NOT_FOUND.getMessage(),
                        dto.getBookingId()), ErrorCode.ERR_BOOKING_NOT_FOUND)));
        booking.setStatus(dto.getStatus());
        String mail = authClient.getUserById(booking.getUserId()).getEmail();
        bookingKafkaProducer.sendMailSendEvent(KafkaConstants.MAIL_SENDER_TOPIC_NAME,
                new KafkaMailDto(MailType.BOOKING_MAIL, mail, MailConstants.PLAHCTOH_BOOKING_MAIL_SUBJECT,
                        String.format(MailConstants.BOOKING_REQUEST_WAS_REJECTED_MAIL_MESSAGE_TEMPLATE,
                                booking.getMeetingRoom().getNumber(), dto.getStatus().name())));
    }

    @Override
    public Set<TimeSlot> findAllBookingsByDateAndMeetingRoom(LocalDate date, UUID meetingRoomId) {

        List<Booking> bookings = bookingRepository.findAllByBookingDateAndMeetingRoomId(date, meetingRoomId);
        log.info(bookings.toString() + "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        Set<TimeSlot> timeSlots = new HashSet<>();
        timeSlots.add(new TimeSlot());
        return null;
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

    @Override
    public Set<BookedTimeSlotDto> findBookedTimeSlotsDto(UUID meetingRoomId, LocalDate bookingDate) {

        if (!meetingRoomService.existsById(meetingRoomId)) {
            throw LogExceptionWrapper.logErrorException(new NotFoundException(String.format(ErrorCode.ERR_MEETING_ROOM_NOT_FOUND.getMessage(),
                    "id", meetingRoomId), ErrorCode.ERR_MEETING_ROOM_NOT_FOUND));
        }

        return findBookedTimeSlots(meetingRoomId, bookingDate);
    }

    private Set<BookedTimeSlotDto> findBookedTimeSlots(UUID meetingRoomId, LocalDate bookingDate) {

        List<Booking> bookings = bookingRepository.findAllByBookingDateAndMeetingRoomId(bookingDate,
                meetingRoomId);

        if (bookings.isEmpty()) {
            return Collections.EMPTY_SET;
        } else {

//UserTeamLeadDto(String teamLeadFirstName, String teamLeadLastName)
            Map<TimeSlot, TimeSlotInformationDto> bookedTimeSlots = new HashMap<>();
            Map<UUID, UserTeamLeadDto> teamLeadInfo = new HashMap<>(); //id того, кто бронировал и его тимлида имя и фамилия

            bookings
                    .stream()
                    .forEach(booking -> booking
                            .getTimeSlots()
                            .forEach(timeSlot -> bookedTimeSlots.put(timeSlot, new TimeSlotInformationDto(booking.getStatus(), booking.getUserId()))));

            bookings
                    .stream()
                    .map(Booking::getUserId)
                    .forEach(userId -> {
                                if( !teamLeadInfo.containsKey(userId)) {
                                    UserDataDTO teamLeadData = userClient.getUserData(departmentClient.getUserById(userId).getTeamLeader().getId());
                                    teamLeadInfo.put(userId, new UserTeamLeadDto(teamLeadData.getFirstName(),teamLeadData.getLastName()));
                                }
                            });

            return bookedTimeSlots
                    .entrySet()
                    .stream()
                    .map(bookedTimeSlot -> new BookedTimeSlotDto(bookedTimeSlot.
                            getKey().getReservationStart(),
                            bookedTimeSlot.getKey().getReservationEnd(),
                            bookedTimeSlot.getValue().getStatus(),
                            teamLeadInfo.get(bookedTimeSlot.getValue().getClientId()).getTeamLeadFirstName(),
                            teamLeadInfo.get(bookedTimeSlot.getValue().getClientId()).getTeamLeadLastName()))
                            .collect(Collectors.toSet());
        }
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


    private void approveBookingRequest(DepartmentUserDTO department, Booking booking) throws JsonProcessingException {

        UUID teamLeadId = department.getTeamLeader().getId();
        String leadMail = authClient.getUserById(teamLeadId).getEmail();

        String userFirstName = userClient.getUser().getFirstName();
        String userLastName = userClient.getUser().getLastName();
        Integer meetRoomNumber = meetingRoomService.findMeetingRoomById(booking.getMeetingRoom().getId()).getNumber();

        ChangeBookingStatusDTO approveDTO = new ChangeBookingStatusDTO(booking.getId(), BookingStatus.APPROVED);
        ChangeBookingStatusDTO rejectDTO = new ChangeBookingStatusDTO(booking.getId(), BookingStatus.REJECTED);
        String encodedApprovePart = Base64.getEncoder().encodeToString(objectMapper.writeValueAsString(approveDTO)
                .getBytes());
        String encodedRejectPart = Base64.getEncoder().encodeToString(objectMapper.writeValueAsString(rejectDTO)
                .getBytes());
        String url = domain + "/api/v1/bookings/approvement/";


        String mailMessage = MailConstants.BOOKING_REQUEST_MAIL_MESSAGE_TEMPATE.formatted(userFirstName,
                userLastName, meetRoomNumber, booking.getBookingDate(), url + encodedApprovePart, url + encodedRejectPart);

        bookingKafkaProducer.sendMailSendEvent(KafkaConstants.MAIL_SENDER_TOPIC_NAME, new KafkaMailDto(
                MailType.BOOKING_APPROVE_REQUEST_MAIL, leadMail, MailConstants.BOOKING_APPROVE_REQUEST_MAIL_SUBJECT,
                mailMessage));
    }

}

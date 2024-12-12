package eu.senla.booking.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "bookings", schema = "booking_service_schema")
//@ToString
//@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "booking_id")
    private UUID id;
    @Column(name = "meeting_room_id")
    private UUID meetingRoomId;
    @Column(name = "booking_date")
    private LocalDate reservationDate;
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinTable(name = "bookings_time_slots",
            joinColumns = @JoinColumn(name = "booking_id"),
            inverseJoinColumns = @JoinColumn(name = "time_slot_id"))
    List<TimeSlot> timeSlots = new ArrayList<>();
    @Column(name = "user_id")
    private UUID userId;

//    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
//    @Column(name = "booking_id")
//    private UUID id;
//
//    @Column(name = "client_id")
//    private UUID clientId;
//
//    @Column(name = "reservation_start")
//    private LocalTime reservationStart;
//
//    @Column(name = "reservation_end")
//    private LocalTime reservationEnd;
//
//    @Column(name = "procedure_id")
//    private UUID procedureId;
//
//    @Column(name = "working_day_id")
//    private UUID workingDayId;
}
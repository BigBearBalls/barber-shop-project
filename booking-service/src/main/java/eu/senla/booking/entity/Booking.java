package eu.senla.booking.entity;

import eu.senla.common.booking.enums.BookingStatus;
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
@ToString
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "booking_id")
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "meeting_room_id")
    private MeetingRoom meetingRoom;
    @Column(name = "booking_date")
    private LocalDate bookingDate;
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinTable(name = "bookings_time_slots",
            joinColumns = @JoinColumn(name = "booking_id"),
            inverseJoinColumns = @JoinColumn(name = "time_slot_id"))
    List<TimeSlot> timeSlots = new ArrayList<>();
    @Column(name = "user_id")
    private UUID userId;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private BookingStatus status;
}
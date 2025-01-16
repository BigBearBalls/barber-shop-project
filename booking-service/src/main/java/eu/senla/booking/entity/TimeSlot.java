package eu.senla.booking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "time_slots", schema = "booking_service_schema")
@EqualsAndHashCode
@ToString
public class TimeSlot {

    @JsonIgnore
    @Id
    @Column(name = "time_slot_id")
    private UUID id;
    @Column(name = "reservation_start")
    private LocalTime reservationStart;
    @Column(name = "reservation_end")
    private LocalTime reservationEnd;
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "bookings_time_slots",
            joinColumns = @JoinColumn(name = "time_slot_id"),
            inverseJoinColumns = @JoinColumn(name = "booking_id"))
    private List<Booking> bookings;
}

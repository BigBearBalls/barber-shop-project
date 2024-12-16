package eu.senla.booking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "time_slots", schema = "booking_service_schema")
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

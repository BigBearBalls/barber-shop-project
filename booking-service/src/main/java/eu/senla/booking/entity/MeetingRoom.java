package eu.senla.booking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "meeting_rooms", schema = "booking_service_schema")
public class MeetingRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "meeting_room_id")
    private UUID id;
    @Column(name = "number")
    private Integer number;
    @JsonIgnore
    @OneToMany
    private Set<Booking> bookings = new HashSet<>();
}

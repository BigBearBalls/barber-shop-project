package eu.senla.booking.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "bookings", schema = "my_schema")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookings_id_gen")
    @SequenceGenerator(name = "bookings_id_gen", sequenceName = "bookings_booking_id_seq", allocationSize = 1)
    @Column(name = "booking_id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "reservation_start", nullable = false)
    private LocalTime reservationStart;

    @NotNull
    @Column(name = "reservation_end", nullable = false)
    private LocalTime reservationEnd;

}
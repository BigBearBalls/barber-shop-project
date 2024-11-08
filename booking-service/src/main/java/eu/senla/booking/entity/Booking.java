package eu.senla.booking.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "bookings")
@ToString
@Builder
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookings_id_gen")
    @SequenceGenerator(name = "bookings_id_gen", sequenceName = "bookings_booking_id_seq", allocationSize = 1)
    @Column(name = "booking_id")
    private Integer id;

    @Column(name = "client_id")
    private UUID clientId;

    @Column(name = "reservation_start")
    private LocalTime reservationStart;

    @Column(name = "reservation_end")
    private LocalTime reservationEnd;

    @Column(name = "procedure_id")
    private Integer procedureId;

    @Column(name = "working_day_id")
    private Integer workingDayId;


}
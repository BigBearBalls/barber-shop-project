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
@Table(name = "bookings", schema = "booking_service_schema")
@ToString
@Builder
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "booking_id")
    private UUID id;

    @Column(name = "client_id")
    private UUID clientId;

    @Column(name = "reservation_start")
    private LocalTime reservationStart;

    @Column(name = "reservation_end")
    private LocalTime reservationEnd;

    @Column(name = "procedure_id")
    private UUID procedureId;

    @Column(name = "working_day_id")
    private UUID workingDayId;


}
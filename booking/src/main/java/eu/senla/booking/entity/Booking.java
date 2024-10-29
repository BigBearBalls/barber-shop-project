package eu.senla.booking.entity;

import eu.senla.booking.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "master_free_time_id")
    private MasterFreeTime masterFreeTime;

    private UUID userId;

    private Long serviceId;

    @Enumerated(EnumType.STRING)
    private Status status;
}

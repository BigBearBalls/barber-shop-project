package eu.senla.booking.entity;

import eu.senla.booking.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "master_free_time")
public class MasterFreeTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID masterId;

    @ManyToOne
    @JoinColumn(name = "time_id")
    private DayTime time;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private Status status;
}

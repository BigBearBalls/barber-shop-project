package eu.senla.booking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "master_free_time")
public class MasterTimeTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID masterId;

    @ManyToOne
    @JoinColumn(name = "time_id")
    private DayTime dayTime;

    private LocalDate date;

    private boolean isAvailable;
}

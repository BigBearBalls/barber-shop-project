package eu.senla.booking.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Setter
@Getter
@Table(name = "master_free_time")
public class MasterTimetable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID masterId;

    @ManyToOne
    @JoinColumn(name = "time_id")
    private DayTime dayTime;

    private LocalDate date;

    @Builder.Default
    private boolean isAvailable = false;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createAt;
}

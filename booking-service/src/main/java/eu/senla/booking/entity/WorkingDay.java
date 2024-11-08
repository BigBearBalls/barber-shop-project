package eu.senla.booking.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "working_days", schema = "booking_service_schema")
@ToString
public class WorkingDay {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "working_days_id_gen")
    @SequenceGenerator(name = "working_days_id_gen", sequenceName = "working_days_working_day_id_seq", allocationSize = 1)
    @Column(name = "working_day_id")
    private Integer id;

    @Column(name = "master_id")
    private UUID master;

    @Column(name = "working_date")
    private LocalDate workingDate;

    @Column(name = "work_start")
    private LocalTime workStart;

    @Column(name = "work_end")
    private LocalTime workEnd;

}
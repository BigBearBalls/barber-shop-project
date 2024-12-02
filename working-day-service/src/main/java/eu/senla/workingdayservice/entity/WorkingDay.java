package eu.senla.workingdayservice.entity;

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
@Table(name = "working_days", schema = "working_day_service_schema")
@ToString
public class WorkingDay {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "working_day_id")
    private UUID id;

    @Column(name = "master_id")
    private UUID masterId;

    @Column(name = "working_date")
    private LocalDate workingDate;

    @Column(name = "work_start")
    private LocalTime workStart;

    @Column(name = "work_end")
    private LocalTime workEnd;

}
package eu.senla.userservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "working_days", schema = "my_schema")
public class WorkingDay {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "working_days_id_gen")
    @SequenceGenerator(name = "working_days_id_gen", sequenceName = "working_days_working_day_id_seq", allocationSize = 1)
    @Column(name = "working_day_id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "master_id", nullable = false)
    private User master;

    @NotNull
    @Column(name = "working_date", nullable = false)
    private LocalDate workingDate;

    @NotNull
    @Column(name = "work_start", nullable = false)
    private LocalTime workStart;

    @NotNull
    @Column(name = "work_end", nullable = false)
    private LocalTime workEnd;

}
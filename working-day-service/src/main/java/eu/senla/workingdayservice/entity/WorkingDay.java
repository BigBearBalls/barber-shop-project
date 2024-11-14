package eu.senla.workingdayservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "working_day", schema = "working_day_service_schema")
@ToString
public class WorkingDay {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "working_days_id_gen")
    @SequenceGenerator(name = "working_days_id_gen", sequenceName = "working_days_working_day_id_seq", allocationSize = 1)
    @Column(name = "working_day_id")
    private Integer id;

    @Column(name = "master_id")
    private UUID masterId;

    @Column(name = "working_date")
    private LocalDate workingDate;

    @Column(name = "work_start")
    private LocalTime workStart;

    @Column(name = "work_end")
    private LocalTime workEnd;

}
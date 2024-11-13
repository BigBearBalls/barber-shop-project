package eu.senla.calendarservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "calendar_days_off", schema = "calendar_service_schema")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CalendarDayOff {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "calendar_day_off_seq")
    @SequenceGenerator(name = "calendar_day_off_seq", sequenceName = "calendar_day_off_id_seq", allocationSize = 1)
    @Column(name = "calendar_day_off_id")
    private long id;
    @Column(name = "calendar_day_off_date")
    private LocalDate date;

}

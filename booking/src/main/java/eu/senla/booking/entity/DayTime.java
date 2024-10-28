package eu.senla.booking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "day_time")
public class DayTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    private LocalTime time;

    @OneToMany(mappedBy = "time", orphanRemoval = true)
    private List<MasterFreeTime> masterFreeTime;
}

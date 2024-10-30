package eu.senla.booking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Table(name = "day_time")
public class DayTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    private LocalTime time;

    @OneToMany(mappedBy = "dayTime", orphanRemoval = true)
    private List<MasterTimetable> masterTimeTable;
}

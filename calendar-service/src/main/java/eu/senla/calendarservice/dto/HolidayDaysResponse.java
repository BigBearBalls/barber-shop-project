package eu.senla.calendarservice.dto;

import eu.senla.calendarservice.entity.DayOff;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HolidayDaysResponse {

    List<DayOff> holidays;

}

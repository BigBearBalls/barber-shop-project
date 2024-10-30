package eu.senla.booking.service;

import eu.senla.booking.dto.DayTimesResponse;
import eu.senla.booking.entity.DayTime;

public interface DayTimeService {

    DayTime getDayTime(Short dayTimeId);

    DayTimesResponse getDayTimes();
}

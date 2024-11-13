package eu.senla.calendarservice.mapper;

import eu.senla.calendarservice.dto.IsHolidayResponse;
import eu.senla.calendarservice.entity.DayOff;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.time.LocalDate;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CalendarMapper {

    @Mapping(target = "id", ignore = true)
    DayOff toCalendarDayOff(LocalDate date);

    IsHolidayResponse toIsHolidayDayResponse(Boolean isHolidayDay);

}

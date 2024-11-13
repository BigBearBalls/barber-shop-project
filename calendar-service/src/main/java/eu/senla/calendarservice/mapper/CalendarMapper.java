package eu.senla.calendarservice.mapper;

import eu.senla.calendarservice.dto.IsHolidayDayResponse;
import eu.senla.calendarservice.entity.CalendarDayOff;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.time.LocalDate;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CalendarMapper {

    @Mapping(target = "id", ignore = true)
    CalendarDayOff toCalendarDayOff(LocalDate date);
    IsHolidayDayResponse toIsHolidayDayResponse(Boolean isHolidayDay);

}

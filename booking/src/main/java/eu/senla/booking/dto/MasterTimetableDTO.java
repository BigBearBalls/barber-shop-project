package eu.senla.booking.dto;

import eu.senla.booking.entity.DayTime;

import java.time.LocalDate;

public record MasterTimetableDTO(
        Long masterTimetableId,
        DayTime dayTime,
        LocalDate date
) {
}

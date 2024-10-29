package eu.senla.booking.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record MasterTimeTableDTO(
        Long masterFreeTimeId,
        LocalTime time,
        LocalDate date
) {
}

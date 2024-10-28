package eu.senla.booking.dto;

import eu.senla.booking.enums.Status;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record GetMasterFreeTimeResponse(
        Long masterFreeTimeId,
        LocalTime time,
        LocalDate date,
        Status status
) {
}

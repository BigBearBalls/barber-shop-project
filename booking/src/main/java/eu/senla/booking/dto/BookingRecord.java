package eu.senla.booking.dto;

import eu.senla.booking.enums.Status;

import java.time.LocalDate;
import java.time.LocalTime;

public record BookingRecord(
        Long bookingId,
        UserDTO master,
        LocalDate date,
        LocalTime time,
        ProcedureDTO procedure,
        Status status
) {
}

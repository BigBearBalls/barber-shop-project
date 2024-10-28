package eu.senla.booking.dto;

import eu.senla.booking.enums.Status;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record BookingRecord(
        Long bookingId,
        UUID masterId,
        LocalDate date,
        LocalTime time,
        Status status
) {
}

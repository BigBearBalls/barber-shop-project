package eu.senla.booking.dto;

import java.time.LocalDate;

public record DateTimeRequest(Short dayTimeId, LocalDate date) {
}

package eu.senla.booking.dto;

import java.util.List;

public record UserBookingsResponse(List<BookingRecord> list) {
}

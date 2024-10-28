package eu.senla.booking.dto;

public record ChangeBookingDateRequest(Long bookingId, Long masterFreeTimeId) {
}

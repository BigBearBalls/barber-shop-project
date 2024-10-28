package eu.senla.booking.dto;

import java.util.UUID;

public record BookTimeRequest(UUID userId, Long serviceId, Long masterFreeTimeId) {
}

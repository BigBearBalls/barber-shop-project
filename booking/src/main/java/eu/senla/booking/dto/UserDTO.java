package eu.senla.booking.dto;

import java.util.UUID;

public record UserDTO(UUID id, String name, String surname) {
}

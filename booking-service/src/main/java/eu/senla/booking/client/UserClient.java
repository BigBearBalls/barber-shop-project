package eu.senla.booking.client;

import eu.senla.booking.dto.UserDTO;

import java.util.UUID;

public interface UserClient {
    UserDTO findUserById(UUID id);
}

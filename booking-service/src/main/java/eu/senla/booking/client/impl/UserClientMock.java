package eu.senla.booking.client.impl;

import eu.senla.booking.client.UserClient;
import eu.senla.booking.dto.UserDTO;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserClientMock implements UserClient {

    @Override
    public UserDTO findUserById(UUID id) {
        return UserDTO.builder()
                .id(id)
                .firstname("Иван")
                .lastname("Иванов")
                .phoneNumber("+375(33)333-33-33")
                .build();
    }

}

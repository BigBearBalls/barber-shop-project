package eu.senla.booking.mock;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserServiceMock {
    public UserMock findUserById(UUID id) {
        return UserMock.builder()
                .id(id)
                .firstname("Иван")
                .lastname("Иванов")
                .phoneNumber("+375(33)333-33-33")
                .build();
    }
}

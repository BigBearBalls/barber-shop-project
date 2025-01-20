package eu.senla.authservice.service;

import eu.senla.authservice.model.User;
import eu.senla.common.dto.UserCredentialsDTO;
import java.util.UUID;

public interface UserService {

    UUID saveUser(User user);

    void deleteUserById(UUID id);

    User findByEmail(String email);

    User findById(UUID id);

    UserCredentialsDTO getUserCredentialsById(UUID id);
}

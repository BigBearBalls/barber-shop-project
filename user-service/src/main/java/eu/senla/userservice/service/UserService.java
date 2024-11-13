package eu.senla.userservice.service;

import eu.senla.userservice.dto.RegistrationRequest;
import eu.senla.userservice.dto.UserDTO;
import eu.senla.userservice.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.UUID;

public interface UserService {

    UserDTO getUserByEmail(String email);

    void createUser(RegistrationRequest registrationRequest);

    String getUserPasswordById(UUID userId);

    UserDetailsService userDetailsService();

    User findByEmail(String email);
}

package eu.senla.authservice.service;

import eu.senla.authservice.dto.RegistrationRequest;
import eu.senla.authservice.dto.UserDTO;
import eu.senla.authservice.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.UUID;

public interface UserService {

    void regUser(RegistrationRequest registrationRequest);

    UserDTO getUserByEmail(String email);

    String getUserPasswordById(UUID id);

    User findByEmail(String email);

    UserDetailsService userDetailsService();
}

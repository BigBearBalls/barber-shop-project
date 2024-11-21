package eu.senla.authservice.service;

import eu.senla.authservice.dto.RegistrationRequest;
import eu.senla.authservice.dto.UserPermissionsManipulationRequest;
import eu.senla.authservice.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.UUID;

public interface UserService {

    UUID regUser(RegistrationRequest registrationRequest);

    void deleteUserById(UUID id);

    User findByEmail(String email);

    User findById(UUID id);

    void addPermissionsToUser(UserPermissionsManipulationRequest request);

    void removeUserPermissions(UserPermissionsManipulationRequest request);

    UserDetailsService userDetailsService();
}

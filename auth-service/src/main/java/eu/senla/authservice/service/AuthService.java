package eu.senla.authservice.service;

import eu.senla.authservice.dto.LoginRequest;
import eu.senla.authservice.dto.LoginResponse;
import eu.senla.authservice.dto.RegistrationRequest;
import eu.senla.authservice.dto.UserCredentialsDTO;

public interface AuthService {

    void regUser(RegistrationRequest registrationRequest);

    LoginResponse loginUser(LoginRequest loginRequest);
}

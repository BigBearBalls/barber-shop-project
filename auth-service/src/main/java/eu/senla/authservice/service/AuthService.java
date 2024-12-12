package eu.senla.authservice.service;

import eu.senla.common.auth.dto.LoginRequest;
import eu.senla.common.auth.dto.LoginResponse;
import eu.senla.common.auth.dto.RegistrationRequest;

public interface AuthService {

    void regUser(RegistrationRequest registrationRequest);

    LoginResponse loginUser(LoginRequest loginRequest);
}

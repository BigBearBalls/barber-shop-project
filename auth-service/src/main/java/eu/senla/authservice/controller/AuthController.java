package eu.senla.authservice.controller;

import eu.senla.authservice.dto.LoginRequest;
import eu.senla.authservice.dto.LoginResponse;
import eu.senla.authservice.dto.RegistrationRequest;
import eu.senla.authservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth/")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("registration")
    void register(@RequestBody RegistrationRequest registrationRequest) {
        authService.regUser(registrationRequest);
    }

    @PostMapping("login")
    LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.loginUser(loginRequest);
    }
}

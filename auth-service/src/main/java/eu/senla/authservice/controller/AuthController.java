package eu.senla.authservice.controller;

import eu.senla.authservice.service.AuthService;
import eu.senla.common.auth.dto.LoginRequest;
import eu.senla.common.auth.dto.LoginResponse;
import eu.senla.common.auth.dto.RegistrationRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth/")
@RequiredArgsConstructor
@Validated
public class AuthController {

    private final AuthService authService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("registration")
    void register(@RequestBody @Valid RegistrationRequest registrationRequest) {
        authService.regUser(registrationRequest);
    }


    @PostMapping("login")
    LoginResponse login(@RequestBody @Valid LoginRequest loginRequest) {
        return authService.loginUser(loginRequest);
    }
}

package eu.senla.authservice.controller;

import eu.senla.authservice.model.User;
import eu.senla.authservice.service.UserService;
import eu.senla.common.dto.UserCredentialsDTO;
import eu.senla.httpconfiguration.security.holder.UserIdHolder;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/internal/users/")
@RequiredArgsConstructor
public class InternalUserController {

    private final UserService userService;

    @GetMapping
    public UserCredentialsDTO getUserCredentials() {
        UUID userId = UserIdHolder.getUserId();
        return userService.getUserCredentialsById(userId);
    }

    @GetMapping("{userId}")
    public UserCredentialsDTO getUserCredentialsById(@PathVariable("userId") UUID userId) {
        return userService.getUserCredentialsById(userId);
    }
}

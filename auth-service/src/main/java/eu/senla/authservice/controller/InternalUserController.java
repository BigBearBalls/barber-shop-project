package eu.senla.authservice.controller;

import eu.senla.authservice.service.UserService;
import eu.senla.common.dto.UserCredentialsDTO;
import eu.senla.httpconfiguration.security.holder.UserHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/internal/users/")
@RequiredArgsConstructor
public class InternalUserController {

    private final UserService userService;

    @GetMapping
    public UserCredentialsDTO getUserCredentials() {
        UUID userId = UserHolder.getUser().getId();
        return userService.getUserCredentialsById(userId);
    }

    @GetMapping("{userId}")
    public UserCredentialsDTO getUserCredentialsById(@PathVariable("userId") UUID userId) {
        return userService.getUserCredentialsById(userId);
    }
}

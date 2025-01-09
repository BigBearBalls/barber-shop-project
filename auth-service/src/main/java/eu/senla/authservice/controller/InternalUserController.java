package eu.senla.authservice.controller;

import eu.senla.authservice.service.UserService;
import eu.senla.common.auth.dto.UserCredentialsDTO;
import eu.senla.httpconfiguration.security.holder.UserHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

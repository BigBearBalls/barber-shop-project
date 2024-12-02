package eu.senla.authservice.controller;

import eu.senla.authservice.dto.UserCredentialsDTO;
import eu.senla.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/internal/users/")
@RequiredArgsConstructor
public class InternalUserController {

    private final UserService userService;

    @GetMapping
    public UserCredentialsDTO getUserCredentialsByEmail(@RequestHeader("X-User-Id") UUID userId) {
        return userService.getUserCredentialsById(userId);
    }
}

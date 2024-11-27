package eu.senla.authservice.controller;

import eu.senla.authservice.dto.UserCredentialsDTO;
import eu.senla.authservice.service.UserService;
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

    @GetMapping("{userEmail}")
    public UserCredentialsDTO getUserCredentialsByEmail(@PathVariable String userEmail) {
        return userService.getUserCredentialsByEmail(userEmail);
    }
}

package eu.senla.userservice.controller;


import eu.senla.userservice.dto.RegistrationRequest;
import eu.senla.userservice.dto.UserDTO;
import eu.senla.userservice.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    UserDTO getUserByEmail(@RequestParam("email") String email) {
        return userService.getUserByEmail(email);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void createUser(@RequestBody RegistrationRequest request) {
        userService.createUser(request);
    }

    @GetMapping(value = "/{userId}/password")
    @PreAuthorize("@headerCheck.hasRequiredHeader(#request)")
    String getUserPasswordById(@PathVariable UUID userId, HttpServletRequest request) {
        return userService.getUserPasswordById(userId);
    }
}

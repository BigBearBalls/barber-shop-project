package eu.senla.userservice.controller;

import eu.senla.userservice.dto.UserDataDTO;
import eu.senla.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/internal/users/")
public class UserControllerInternal {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody UserDataDTO dto) {
        userService.createUser(dto);
    }

    @GetMapping(value = "/{userId}")
    public UserDataDTO getUserById(@PathVariable UUID userId) {
        return userService.getUserById(userId);
    }
}

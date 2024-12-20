package eu.senla.userservice.controller;

import eu.senla.common.account.dto.FindUsersAccountsRequest;
import eu.senla.common.dto.UserDataDTO;
import eu.senla.common.user.dto.UsersDataResponse;
import eu.senla.httpconfiguration.security.holder.UserHolder;
import eu.senla.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
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

    @GetMapping
    public UserDataDTO getUser() {
        UUID userId = UserHolder.getUser().getId();
        return userService.getUserById(userId);
    }

    @GetMapping("{userId}")
    public UserDataDTO getUserData(@PathVariable("userId") UUID userId) {
        return userService.getUserById(userId);
    }

    @GetMapping("/search")
    public UsersDataResponse searchUsers(FindUsersAccountsRequest request) {
        return userService.searchUsers(request);
    }
}

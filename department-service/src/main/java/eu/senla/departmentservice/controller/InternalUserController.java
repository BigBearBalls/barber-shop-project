package eu.senla.departmentservice.controller;

import eu.senla.common.department.dto.request.CreateDepartmentUserRequest;
import eu.senla.common.department.dto.response.DepartmentUserDTO;
import eu.senla.common.department.dto.response.ShortDepartmentUserInfoDTO;
import eu.senla.departmentservice.service.UserService;
import eu.senla.httpconfiguration.security.holder.UserIdHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/internal/users/")
public class InternalUserController {

    private final UserService userService;

    /**
     * Save department user
     * @param request data needed for user create operation
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(@RequestBody CreateDepartmentUserRequest request) {
        userService.saveUser(request);
    }

    @GetMapping
    public DepartmentUserDTO getUser() {
        UUID uuid = UserIdHolder.getUserId();
        return userService.getUserDTOById(uuid);
    }

    @GetMapping("{userId}")
    public DepartmentUserDTO getUserById(@PathVariable("userId") UUID userId) {
        return userService.getUserDTOById(userId);
    }

    @GetMapping("{userId}/preview")
    public ShortDepartmentUserInfoDTO getShortUserInfo(@PathVariable("userId") UUID userId) {
        return userService.getUserShortInfo(userId);
    }
}

package eu.senla.departmentservice.controller;

import eu.senla.common.department.dto.request.NewUserDepartmentRole;
import eu.senla.departmentservice.service.UserService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/departments/users/")
public class UserController {

    private final UserService userService;

    @PatchMapping("{userId}/roles")
    public void setUserRole(@PathVariable("userId") UUID userId, @RequestBody NewUserDepartmentRole role) {
        userService.setUserRole(userId, role.getRole());
    }
}

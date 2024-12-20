package eu.senla.departmentservice.controller;

import eu.senla.common.department.dto.request.NewUserDepartmentRole;
import eu.senla.common.department.dto.response.ShortDepartmentUserInfoDTO;
import eu.senla.common.enums.DepartmentRole;
import eu.senla.departmentservice.model.Department;
import eu.senla.departmentservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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

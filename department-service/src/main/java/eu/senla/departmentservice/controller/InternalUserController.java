package eu.senla.departmentservice.controller;

import eu.senla.common.department.dto.request.NewDepartmentUserRequest;
import eu.senla.common.department.dto.response.DepartmentUserDTO;
import eu.senla.common.department.dto.response.ShortDepartmentUserInfoDTO;
import eu.senla.departmentservice.model.Department;
import eu.senla.departmentservice.service.DepartmentService;
import eu.senla.departmentservice.service.UserService;
import eu.senla.httpconfiguration.security.holder.UserHolder;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/internal/users/")
public class InternalUserController {

    private final UserService userService;
    private final DepartmentService departmentService;

    @GetMapping
    public DepartmentUserDTO getUser() {
        UUID uuid = UserHolder.getUser().getId();
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

    @PostMapping
    public void createUser(@RequestBody NewDepartmentUserRequest departmentUserRequest) {
        Department department = departmentService.getDepartmentByTeamLeaderId(departmentUserRequest.getTeamLeaderId());
        userService.createUser(departmentUserRequest.getUserId(), department);
    }
}

package eu.senla.authservice.controller;

import eu.senla.authservice.annotation.CheckPermission;
import eu.senla.authservice.dto.PermissionsDTO;
import eu.senla.authservice.dto.UserPermissionsManipulationRequest;
import eu.senla.authservice.enums.PermissionValue;
import eu.senla.authservice.model.User;
import eu.senla.authservice.service.PermissionService;
import eu.senla.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/permissions/")
public class PermissionController {

    private final UserService userService;
    private final PermissionService permissionService;

    @GetMapping
    @CheckPermission(value = PermissionValue.VIEW_PERMISSIONS)
    public PermissionsDTO getPermissions() {
        return permissionService.getPermissions();
    }

    @PostMapping("user")
    @CheckPermission(value = PermissionValue.ADD_PERMISSION)
    public void addPermissionsToUser(@RequestBody UserPermissionsManipulationRequest permissions) {
        permissionService.addPermissionsToUser(permissions);
    }

    @DeleteMapping("user")
    @CheckPermission(value = PermissionValue.REMOVE_PERMISSION)
    public void removeUserPermissions(@RequestBody UserPermissionsManipulationRequest permissions) {
        permissionService.removeUserPermissions(permissions);
    }

    @GetMapping("user")
    @CheckPermission(value = PermissionValue.VIEW_SELF_PERMISSIONS)
    public PermissionsDTO getUserPermissions() {
        String email = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail();
        return permissionService.getUserPermissions(email);
    }
}

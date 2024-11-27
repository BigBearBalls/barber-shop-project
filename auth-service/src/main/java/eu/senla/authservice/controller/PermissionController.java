package eu.senla.authservice.controller;

import eu.senla.authservice.dto.PermissionsDTO;
import eu.senla.authservice.dto.UserPermissionsManipulationRequest;
import eu.senla.authservice.model.Permission;
import eu.senla.authservice.model.User;
import eu.senla.authservice.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/permissions/")
@RequiredArgsConstructor
public class PermissionController {

    private final PermissionService permissionService;

    @PostMapping("user")
    public void addPermissionsToUser(@RequestBody UserPermissionsManipulationRequest request) {
        permissionService.addPermissionsToUser(request);
    }

    @DeleteMapping("user")
    public void removeUserPermissions(@RequestBody UserPermissionsManipulationRequest request) {
        permissionService.removeUserPermissions(request);
    }

    @GetMapping("user")
    public PermissionsDTO getUserPermissions() {
        String email = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail();
        return permissionService.getUserPermissions(email);
    }

    @GetMapping
    public PermissionsDTO getUserPermissionsList() {
        return permissionService.getPermissions();
    }
}

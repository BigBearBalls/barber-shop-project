package eu.senla.authservice.controller;

import eu.senla.authservice.service.PermissionService;
import eu.senla.common.auth.dto.PermissionsDTO;
import eu.senla.common.auth.dto.UserPermissionsManipulationRequest;
import eu.senla.httpconfiguration.security.holder.UserHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
        UUID userId = UserHolder.getUser().getId();
        return permissionService.getUserPermissions(userId);
    }

    @GetMapping
    public PermissionsDTO getPermissions() {
        return permissionService.getPermissions();
    }
}

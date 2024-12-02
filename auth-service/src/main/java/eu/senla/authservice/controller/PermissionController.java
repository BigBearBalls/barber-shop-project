package eu.senla.authservice.controller;

import eu.senla.authservice.dto.PermissionsDTO;
import eu.senla.authservice.dto.UserPermissionsManipulationRequest;
import eu.senla.authservice.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public PermissionsDTO getUserPermissions(@RequestHeader(name = "X-User-Id") UUID userId) {
        return permissionService.getUserPermissions(userId);
    }

    @GetMapping
    public PermissionsDTO getPermissions() {
        return permissionService.getPermissions();
    }
}

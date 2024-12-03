package eu.senla.authservice.service;

import eu.senla.authservice.model.Permission;
import eu.senla.common.auth.dto.PermissionsDTO;
import eu.senla.common.auth.dto.UserPermissionsManipulationRequest;
import eu.senla.common.enums.PermissionValue;

import java.util.Set;
import java.util.UUID;

public interface PermissionService {

    Set<Permission> getDefaultUserPermissions();

    Set<Permission> getPermissions(Set<PermissionValue> permissionValues);

    Permission getPermission(PermissionValue permissionValue);

    PermissionsDTO getUserPermissions(UUID userId);

    PermissionsDTO getPermissions();

    void addPermissionsToUser(UserPermissionsManipulationRequest request);

    void removeUserPermissions(UserPermissionsManipulationRequest request);
}

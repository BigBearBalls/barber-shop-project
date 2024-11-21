package eu.senla.authservice.service;

import eu.senla.authservice.dto.PermissionsDTO;
import eu.senla.authservice.enums.PermissionValue;
import eu.senla.authservice.model.Permission;

import java.util.Set;

public interface PermissionService {

    Set<Permission> getDefaultUserPermissions();

    Set<Permission> getPermissions(Set<PermissionValue> permissionValues);

    Permission getPermission(PermissionValue permissionValue);

    PermissionsDTO getPermissions();
}

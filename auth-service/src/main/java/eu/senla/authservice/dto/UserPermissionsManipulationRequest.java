package eu.senla.authservice.dto;

import eu.senla.authservice.enums.PermissionValue;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class UserPermissionsManipulationRequest {

    UUID userId;
    Set<PermissionValue> permissionValues;
}

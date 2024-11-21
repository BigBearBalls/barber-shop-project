package eu.senla.authservice.dto;

import eu.senla.authservice.enums.PermissionValue;
import lombok.Data;

import java.util.Set;

@Data
public class PermissionsDTO {

    Set<PermissionValue> permissionValues;
}

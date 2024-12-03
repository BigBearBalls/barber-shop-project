package eu.senla.common.auth.dto;

import eu.senla.common.enums.PermissionValue;
import lombok.Data;

import java.util.Set;

@Data
public class PermissionsDTO {

    Set<PermissionValue> permissionValues;
}

package eu.senla.authservice.mapper;

import eu.senla.authservice.model.Permission;
import eu.senla.common.auth.dto.PermissionsDTO;
import eu.senla.common.enums.PermissionValue;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PermissionMapper {

    default PermissionsDTO toDTO(Set<Permission> permissions) {
        Set<PermissionValue> permissionValues = permissions.stream().map(Permission::getPermissionValue)
                .collect(Collectors.toSet());
        PermissionsDTO permissionsDTO = new PermissionsDTO();
        permissionsDTO.setPermissionValues(permissionValues);
        return permissionsDTO;
    }
}


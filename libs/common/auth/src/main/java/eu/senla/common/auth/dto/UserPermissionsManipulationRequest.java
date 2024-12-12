package eu.senla.common.auth.dto;

import eu.senla.common.constant.ValidationConstants;
import eu.senla.common.enums.PermissionValue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserPermissionsManipulationRequest {

    @NotBlank(message = ValidationConstants.USER_ID_CANNOT_BE_NULL_VALIDATION_MESSAGE)
    UUID userId;

    @Size(min = ValidationConstants.MIN_PERMISSIONS_LIST_SIZE,
            message = ValidationConstants.SIZE_OF_PERMISSIONS_LIST_CANNOT_BE_LESS_THEN_VALIDATION_MESSAGE)
    Set<PermissionValue> permissionValues;
}

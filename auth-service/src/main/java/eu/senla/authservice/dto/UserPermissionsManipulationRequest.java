package eu.senla.authservice.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import eu.senla.authservice.constant.ValidationConstants;
import eu.senla.authservice.enums.PermissionValue;
import eu.senla.authservice.serialization.TrimStringDeserializer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

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

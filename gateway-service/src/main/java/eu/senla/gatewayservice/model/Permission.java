package eu.senla.gatewayservice.model;

import eu.senla.common.enums.PermissionValue;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Permission implements GrantedAuthority {

    private Integer id;

    private PermissionValue permissionValue;

    @Override
    public String getAuthority() {
        return permissionValue.name();
    }
}
package eu.senla.authservice.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Role implements GrantedAuthority {

    private Integer id;

    private RoleValue roleValue;

    @Override
    public String getAuthority() {
        return roleValue.name();
    }
}
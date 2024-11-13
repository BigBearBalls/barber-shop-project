package eu.senla.authservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Role implements GrantedAuthority {

    private Short id;

    private RoleValue roleValue;

    @Override
    @JsonIgnore
    public String getAuthority() {
        return roleValue.name();
    }
}
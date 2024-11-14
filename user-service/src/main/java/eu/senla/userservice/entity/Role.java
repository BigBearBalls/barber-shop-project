package eu.senla.userservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "roles", schema = "user_service_schema")
public class Role implements GrantedAuthority {

    @Id
    @Column(name = "role_id")
    private Short id;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_value")
    private RoleValue roleValue;

    @Override
    @JsonIgnore
    public String getAuthority() {
        return roleValue.name();
    }
}
package eu.senla.authservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "role", schema = "auth_service_schema")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Short id;

    @Column(name = "role_value")
    @Enumerated(EnumType.STRING)
    private RoleValue roleValue;

    @Override
    @JsonIgnore
    public String getAuthority() {
        return roleValue.name();
    }
}
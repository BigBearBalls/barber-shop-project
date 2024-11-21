package eu.senla.authservice.model;

import eu.senla.authservice.enums.PermissionValue;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "permission", schema = "auth_service_schema")
public class Permission implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_id")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "permission_value")
    private PermissionValue permissionValue;

    @Override
    public String getAuthority() {
        return permissionValue.name();
    }
}

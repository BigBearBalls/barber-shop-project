package eu.senla.gatewayservice.dto;

import eu.senla.gatewayservice.model.Permission;
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
public class UserCredentialsByAccessToken {

    private String email;

    private UUID userId;

    private Set<Permission> permissions;
}

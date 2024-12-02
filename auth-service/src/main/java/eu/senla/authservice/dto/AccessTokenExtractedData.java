package eu.senla.authservice.dto;

import eu.senla.authservice.model.Permission;
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
public class AccessTokenExtractedData {

    private String email;

    private UUID userId;

    private Set<Permission> permissions;

}

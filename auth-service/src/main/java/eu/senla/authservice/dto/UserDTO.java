package eu.senla.authservice.dto;

import eu.senla.authservice.model.Role;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserDTO {

    private UUID id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private Role role;
}

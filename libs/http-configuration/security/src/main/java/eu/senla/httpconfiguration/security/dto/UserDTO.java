package eu.senla.httpconfiguration.security.dto;

import eu.senla.common.enums.DepartmentRole;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO {

    private UUID id;
    private String email;
    private DepartmentRole role;
}

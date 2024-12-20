package eu.senla.common.account.dto;

import eu.senla.common.enums.DepartmentRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PreviewAccountDTO {
    private String id;
    private String firstName;
    private String lastName;
    private DepartmentRole role;
}

package eu.senla.common.department.dto.request;

import eu.senla.common.enums.DepartmentRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NewUserDepartmentRole {
    DepartmentRole role;
}

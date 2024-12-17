package eu.senla.common.department.dto.response;

import eu.senla.common.enums.DepartmentRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DepartmentUserDTO {

    private UUID id;
    private String departmentName;
    private ShortDepartmentUserInfoDTO teamLeader;
    private DepartmentRole role;
}

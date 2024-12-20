package eu.senla.common.department.dto.response;

import eu.senla.common.enums.DepartmentRole;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DepartmentUserDTO {

    private UUID id;
    private String departmentName;
    private ShortDepartmentUserInfoDTO teamLeader;
    private DepartmentRole role;
}

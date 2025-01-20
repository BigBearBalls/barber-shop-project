package eu.senla.common.department.dto.response;

import eu.senla.common.enums.DepartmentRole;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class DepartmentUserDTO {

    private UUID id;
    private String departmentName;
    private ShortDepartmentUserInfoDTO teamLeader;
    private DepartmentRole role;
}

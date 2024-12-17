package eu.senla.common.account.dto;

import eu.senla.common.enums.DepartmentRole;
import lombok.Data;

@Data
public class AccountDetailsDTO {
    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String departmentName;
    private PreviewAccountDTO teamLeader;
    private DepartmentRole role;
}

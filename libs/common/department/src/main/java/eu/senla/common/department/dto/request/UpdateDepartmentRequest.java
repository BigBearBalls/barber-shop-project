package eu.senla.common.department.dto.request;

import eu.senla.common.constant.ValidationConstants;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateDepartmentRequest {

    @Size(min = ValidationConstants.MIN_LENGTH_OF_DEPARTMENT_NAME,
            max = ValidationConstants.MAX_LENGTH_OF_DEPARTMENT_NAME,
            message = ValidationConstants.DEPARTMENT_NAME_LENGTH_MUST_BE_BETWEEN_VALIDATION_MESSAGE)
    private String departmentName;
    private UUID teamLeaderId;
}

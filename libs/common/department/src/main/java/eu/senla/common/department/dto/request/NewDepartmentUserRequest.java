package eu.senla.common.department.dto.request;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewDepartmentUserRequest {
    private UUID userId;
    private UUID teamLeaderId;
}

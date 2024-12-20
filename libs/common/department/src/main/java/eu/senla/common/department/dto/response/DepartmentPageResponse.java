package eu.senla.common.department.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DepartmentPageResponse {

    private List<DepartmentDTO> departments;
    private long totalElements;
}

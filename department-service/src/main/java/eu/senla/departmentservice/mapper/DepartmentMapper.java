package eu.senla.departmentservice.mapper;

import eu.senla.common.department.dto.request.CreateDepartmentRequest;
import eu.senla.common.department.dto.response.DepartmentDTO;
import eu.senla.common.department.dto.response.DepartmentPageResponse;
import eu.senla.departmentservice.model.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DepartmentMapper {

    @Mapping(target = "departmentName", source = "departmentName")
    @Mapping(target = "users", ignore = true)
    @Mapping(target = "teamLeaderId", ignore = true)
    @Mapping(target = "id", ignore = true)
    Department toDepartment(CreateDepartmentRequest createDepartmentRequest);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "departmentName", source = "departmentName")
    @Mapping(target = "teamLeaderId", source = "teamLeaderId")
    DepartmentDTO toDepartmentDTO(Department department);

    default DepartmentPageResponse toDepartmentPageResponse(List<Department> departments, long totalCount) {
        return new DepartmentPageResponse(departments.stream().map(this::toDepartmentDTO).toList(), totalCount);
    }
}

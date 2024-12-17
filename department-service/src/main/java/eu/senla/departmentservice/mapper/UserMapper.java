package eu.senla.departmentservice.mapper;

import eu.senla.common.department.dto.request.CreateDepartmentUserRequest;
import eu.senla.common.department.dto.response.DepartmentUserDTO;
import eu.senla.common.department.dto.response.ShortDepartmentUserInfoDTO;
import eu.senla.common.department.dto.response.ShortDepartmentUserInfoResponse;
import eu.senla.departmentservice.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "department", ignore = true)
    @Mapping(target = "role", ignore = true)
    User toUser(CreateDepartmentUserRequest request);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "departmentName", source = "department.departmentName")
    @Mapping(target = "teamLeader", ignore = true)
    @Mapping(target = "role", source = "role")
    DepartmentUserDTO toDepartmentUserDTO(User user);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "role", source = "role")
    ShortDepartmentUserInfoDTO toShortDepartmentUserInfoDTO(User user);

    default ShortDepartmentUserInfoResponse toShortDepartmentUserInfoResponse(Set<User> users) {
        return new ShortDepartmentUserInfoResponse(users.stream().map(this::toShortDepartmentUserInfoDTO)
                .collect(Collectors.toSet()));
    }
}

package eu.senla.userservice.mapper;

import eu.senla.common.account.dto.AccountDetailsDTO;
import eu.senla.common.account.dto.PreviewAccountDTO;
import eu.senla.common.department.dto.response.DepartmentUserDTO;
import eu.senla.common.department.dto.response.ShortDepartmentUserInfoDTO;
import eu.senla.common.dto.UserCredentialsDTO;
import eu.senla.common.dto.UserDataDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AccountMapper {
    @Mapping(target = "id", source = "userCred.id")
    @Mapping(target = "email", source = "userCred.email")
    @Mapping(target = "firstName", source = "userDataDTO.firstName")
    @Mapping(target = "lastName", source = "userDataDTO.lastName")
    @Mapping(target = "phoneNumber", source = "userDataDTO.phoneNumber")
    @Mapping(target = "departmentName", source = "departmentUserDTO.departmentName")
    @Mapping(target = "teamLeader", source = "previewAccountDTO")
    @Mapping(target = "role", source = "departmentUserDTO.role")
    AccountDetailsDTO toDTO(UserCredentialsDTO userCred, UserDataDTO userDataDTO, DepartmentUserDTO departmentUserDTO,
                            PreviewAccountDTO previewAccountDTO);

    @Mapping(target = "id", source = "shortDepartmentUserInfoDTO.id")
    @Mapping(target = "role", source = "shortDepartmentUserInfoDTO.role")
    @Mapping(target = "lastName", source = "userDataDTO.lastName")
    @Mapping(target = "firstName", source = "userDataDTO.firstName")
    PreviewAccountDTO toDTO(UserDataDTO userDataDTO, ShortDepartmentUserInfoDTO shortDepartmentUserInfoDTO);
}

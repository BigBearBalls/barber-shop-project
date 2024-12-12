package eu.senla.authservice.mapper;

import eu.senla.authservice.model.User;
import eu.senla.common.auth.dto.RegistrationRequest;
import eu.senla.common.dto.UserDataDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "permissions", ignore = true)
    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", source = "password")
    User toEntity(RegistrationRequest dto);

    @Mapping(target = "phoneNumber", source = "phoneNumber")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "id", ignore = true)
    UserDataDTO toUserInfoDTO(RegistrationRequest request);
}

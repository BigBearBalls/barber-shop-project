package eu.senla.userservice.mapper;

import eu.senla.userservice.dto.RegistrationRequest;
import eu.senla.userservice.dto.UserDTO;
import eu.senla.userservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    @Mapping(target = "email", source = "email")
    @Mapping(target = "phoneNumber", source = "phoneNumber")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "role", source = "role")
    @Mapping(target = "id", source = "id")
    UserDTO toDTO(User user);

    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "phoneNumber", source = "phoneNumber")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    User toNewEntity(RegistrationRequest registrationRequest);

    @Mapping(target = "email", source = "email")
    @Mapping(target = "phoneNumber", source = "phoneNumber")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "role", source = "role")
    @Mapping(target = "id", source = "id")
    @Mapping(target = "password", ignore = true)
    User toEntity(UserDTO dto);
}

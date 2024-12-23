package eu.senla.userservice.mapper;

import eu.senla.common.user.dto.UserDataDTO;
import eu.senla.common.user.dto.UsersDataResponse;
import eu.senla.userservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    @Mapping(target = "phoneNumber", source = "phoneNumber")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "id", source = "id")
    UserDataDTO toDTO(User user);

    @Mapping(target = "phoneNumber", source = "phoneNumber")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "id", source = "id")
    User toEntity(UserDataDTO dto);

    default UsersDataResponse toUsersDataResponse(List<User> users) {
        return new UsersDataResponse(users.stream().map(this::toDTO).toList());
    }
}

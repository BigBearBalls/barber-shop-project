package eu.senla.authservice.mapper;

import eu.senla.authservice.dto.AccountDetailsDTO;
import eu.senla.authservice.dto.UserDataDTO;
import eu.senla.authservice.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AccountMapper {

    @Mapping(target = "id", source = "user.id")
    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "firstName", source = "userDataDTO.firstName")
    @Mapping(target = "lastName", source = "userDataDTO.lastName")
    @Mapping(target = "phoneNumber", source = "userDataDTO.phoneNumber")
    AccountDetailsDTO toDTO(User user, UserDataDTO userDataDTO);
}

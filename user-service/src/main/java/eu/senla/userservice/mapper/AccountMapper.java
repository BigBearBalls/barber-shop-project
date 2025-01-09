package eu.senla.userservice.mapper;

import eu.senla.common.account.dto.AccountDetailsDTO;
import eu.senla.common.auth.dto.UserCredentialsDTO;
import eu.senla.common.user.dto.UserDataDTO;
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
    AccountDetailsDTO toDTO(UserCredentialsDTO userCred, UserDataDTO userDataDTO);
}

package eu.senla.accountservice.mapper;

import eu.senla.accountservice.dto.AccountDetailsDTO;
import eu.senla.accountservice.dto.UserCredentialsDTO;
import eu.senla.accountservice.dto.UserDataDTO;
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

package eu.senla.userservice.service;

import eu.senla.common.user.dto.UserDataDTO;

import java.util.UUID;

public interface UserService {

    void createUser(UserDataDTO dto);

    UserDataDTO getUserById(UUID userId);
}

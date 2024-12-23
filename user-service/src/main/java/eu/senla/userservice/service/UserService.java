package eu.senla.userservice.service;

import eu.senla.common.account.dto.FindUsersAccountsRequest;
import eu.senla.common.user.dto.UserDataDTO;
import eu.senla.common.user.dto.UsersDataResponse;

import java.util.UUID;

public interface UserService {

    void createUser(UserDataDTO dto);

    UserDataDTO getUserById(UUID userId);

    UsersDataResponse searchUsers(FindUsersAccountsRequest request);
}

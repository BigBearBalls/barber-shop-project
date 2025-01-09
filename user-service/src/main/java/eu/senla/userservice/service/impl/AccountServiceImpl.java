package eu.senla.userservice.service.impl;

import eu.senla.common.account.dto.AccountDetailsDTO;
import eu.senla.common.auth.dto.UserCredentialsDTO;
import eu.senla.common.user.dto.UserDataDTO;
import eu.senla.userservice.client.UserCredentialsClient;
import eu.senla.userservice.mapper.AccountMapper;
import eu.senla.userservice.service.AccountService;
import eu.senla.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final UserCredentialsClient userCredentialsClient;
    private final UserService userService;
    private final AccountMapper accountMapper;

    @Override
    public AccountDetailsDTO getAccountDetails(UUID userId) {
        UserDataDTO userData = userService.getUserById(userId);

        UserCredentialsDTO userCredentialsDTO = userCredentialsClient.getUserCredentials();

        return accountMapper.toDTO(userCredentialsDTO, userData);
    }
}

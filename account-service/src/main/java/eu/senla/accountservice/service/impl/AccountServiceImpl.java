package eu.senla.accountservice.service.impl;

import eu.senla.accountservice.client.UserCredentialsClient;
import eu.senla.accountservice.client.UserDataClient;
import eu.senla.accountservice.mapper.AccountMapper;
import eu.senla.accountservice.service.AccountService;
import eu.senla.common.account.dto.AccountDetailsDTO;
import eu.senla.common.dto.UserCredentialsDTO;
import eu.senla.common.dto.UserDataDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final UserCredentialsClient userCredentialsClient;
    private final UserDataClient userDataClient;
    private final AccountMapper accountMapper;

    @Override
    public AccountDetailsDTO getAccountDetails() {
        UserCredentialsDTO user = userCredentialsClient.getUserCredentials();
        UserDataDTO userData = userDataClient.getUserData();
        return accountMapper.toDTO(user, userData);
    }
}

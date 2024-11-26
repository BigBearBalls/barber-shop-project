package eu.senla.accountservice.service.impl;

import eu.senla.accountservice.client.UserCredentialsClient;
import eu.senla.accountservice.client.UserDataClient;
import eu.senla.accountservice.dto.AccountDetailsDTO;
import eu.senla.accountservice.dto.UserCredentialsDTO;
import eu.senla.accountservice.dto.UserDataDTO;
import eu.senla.accountservice.mapper.AccountMapper;
import eu.senla.accountservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final UserCredentialsClient userCredentialsClient;
    private final UserDataClient userDataClient;
    private final AccountMapper accountMapper;

    @Override
    public AccountDetailsDTO getAccountDetails(String email) {
        UserCredentialsDTO user = userCredentialsClient.getUserCredentialsByEmail(email);
        UserDataDTO userData = userDataClient.getUserDataById(user.getId());
        return accountMapper.toDTO(user, userData);
    }
}

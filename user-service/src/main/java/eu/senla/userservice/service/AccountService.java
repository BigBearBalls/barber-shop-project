package eu.senla.userservice.service;

import eu.senla.common.account.dto.AccountDetailsDTO;
import eu.senla.common.account.dto.FindUsersAccountsRequest;
import eu.senla.common.account.dto.PreviewAccountDTO;
import eu.senla.common.user.dto.UsersDataResponse;

import java.util.UUID;

public interface AccountService {

    AccountDetailsDTO getAccountDetails();

    AccountDetailsDTO getAccountDetails(UUID userId);

    PreviewAccountDTO getPreviewAccount(UUID userId);

    UsersDataResponse findUsersAccounts(FindUsersAccountsRequest request);
}

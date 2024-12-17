package eu.senla.userservice.service;

import eu.senla.common.account.dto.AccountDetailsDTO;
import java.util.UUID;

public interface AccountService {

    AccountDetailsDTO getAccountDetails(UUID userId);
}

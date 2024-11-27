package eu.senla.accountservice.service;

import eu.senla.accountservice.dto.AccountDetailsDTO;

public interface AccountService {

    AccountDetailsDTO getAccountDetails(String email);
}

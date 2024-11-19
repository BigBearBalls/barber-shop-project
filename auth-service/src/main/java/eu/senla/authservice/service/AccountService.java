package eu.senla.authservice.service;

import eu.senla.authservice.dto.AccountDetailsDTO;

public interface AccountService {

    AccountDetailsDTO getAccountDetails(String email);
}

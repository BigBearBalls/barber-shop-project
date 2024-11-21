package eu.senla.authservice.service;

import eu.senla.authservice.dto.AccountDetailsDTO;
import eu.senla.authservice.dto.PermissionsDTO;

public interface AccountService {

    AccountDetailsDTO getAccountDetails(String email);

    PermissionsDTO getAccountPermissions(String email);
}

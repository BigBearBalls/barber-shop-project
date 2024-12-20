package eu.senla.accountservice.controller;

import eu.senla.accountservice.service.AccountService;
import eu.senla.common.account.dto.AccountDetailsDTO;
import eu.senla.common.account.dto.FindUsersAccountsRequest;
import eu.senla.common.account.dto.PreviewAccountDTO;
import eu.senla.common.account.dto.PreviewsAccountsResponse;
import eu.senla.common.user.dto.UsersDataResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/accounts/")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public AccountDetailsDTO getAccount() {
        return accountService.getAccountDetails();
    }

    @GetMapping("/{userId}")
    public AccountDetailsDTO getAccountById(@PathVariable("userId") UUID userId) {
        return accountService.getAccountDetails(userId);
    }

    @GetMapping("/{userId}/preview")
    public PreviewAccountDTO getPreviewAccountById(@PathVariable("userId") UUID userId) {
        return accountService.getPreviewAccount(userId);
    }

    @GetMapping("/search")
    public UsersDataResponse findUsersAccounts(FindUsersAccountsRequest findUsersAccountsRequest) {
        return accountService.findUsersAccounts(findUsersAccountsRequest);
    }
}

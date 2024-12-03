package eu.senla.accountservice.controller;

import eu.senla.accountservice.service.AccountService;
import eu.senla.common.account.dto.AccountDetailsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/account/")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public AccountDetailsDTO getAccount() {
        return accountService.getAccountDetails();
    }
}

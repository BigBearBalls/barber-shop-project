package eu.senla.userservice.controller;

import eu.senla.common.account.dto.AccountDetailsDTO;
import eu.senla.httpconfiguration.security.holder.UserHolder;
import eu.senla.userservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/accounts/")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public AccountDetailsDTO getAccount() {
        UUID userId = UserHolder.getUser().getId();
        return accountService.getAccountDetails(userId);
    }
}
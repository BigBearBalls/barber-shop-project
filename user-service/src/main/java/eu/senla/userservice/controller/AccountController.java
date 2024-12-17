package eu.senla.userservice.controller;

import eu.senla.common.account.dto.AccountDetailsDTO;
import eu.senla.userservice.service.AccountService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/accounts/")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public AccountDetailsDTO getAccount(@RequestHeader("X-User-Id") String userId) {

        return accountService.getAccountDetails(UUID.fromString(userId));
    }
}
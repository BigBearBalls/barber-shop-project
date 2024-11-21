package eu.senla.authservice.controller;

import eu.senla.authservice.annotation.CheckPermission;
import eu.senla.authservice.dto.AccountDetailsDTO;
import eu.senla.authservice.dto.PermissionsDTO;
import eu.senla.authservice.enums.PermissionValue;
import eu.senla.authservice.model.User;
import eu.senla.authservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/account/")
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public AccountDetailsDTO getAccount() {
        String email = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail();
        return accountService.getAccountDetails(email);
    }

    @GetMapping("/permissions")
    @CheckPermission(value = PermissionValue.VIEW_SELF_PERMISSIONS)
    public PermissionsDTO getPermissions() {
        String email = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail();
        return accountService.getAccountPermissions(email);
    }
}

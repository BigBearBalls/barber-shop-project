package eu.senla.authservice.service.impl;

import eu.senla.authservice.client.UserDataClient;
import eu.senla.authservice.dto.AccountDetailsDTO;
import eu.senla.authservice.dto.PermissionsDTO;
import eu.senla.authservice.dto.UserDataDTO;
import eu.senla.authservice.mapper.AccountMapper;
import eu.senla.authservice.mapper.PermissionMapper;
import eu.senla.authservice.model.Permission;
import eu.senla.authservice.model.User;
import eu.senla.authservice.service.AccountService;
import eu.senla.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final UserService userService;
    private final UserDataClient userDataClient;
    private final AccountMapper accountMapper;
    private final PermissionMapper permissionMapper;

    @Override
    public AccountDetailsDTO getAccountDetails(String email) {
        User user = userService.findByEmail(email);
        UserDataDTO userData = userDataClient.getUserById(user.getId());
        return accountMapper.toDTO(user, userData);
    }

    @Override
    public PermissionsDTO getAccountPermissions(String email) {
        User user = userService.findByEmail(email);
        Set<Permission> permissions = user.getPermissions();
        return permissionMapper.toDTO(permissions);
    }
}

package eu.senla.authservice.service.impl;

import eu.senla.authservice.client.UserDataClient;
import eu.senla.authservice.component.JwtUtils;
import eu.senla.authservice.dto.LoginRequest;
import eu.senla.authservice.dto.LoginResponse;
import eu.senla.authservice.dto.RegistrationRequest;
import eu.senla.authservice.dto.UserDataDTO;
import eu.senla.authservice.enums.ErrorCode;
import eu.senla.authservice.exception.AuthenticationException;
import eu.senla.authservice.mapper.UserMapper;
import eu.senla.authservice.model.Permission;
import eu.senla.authservice.model.User;
import eu.senla.authservice.service.AuthService;
import eu.senla.authservice.service.PermissionService;
import eu.senla.authservice.service.UserService;
import eu.senla.authservice.utility.CallbackExceptionWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final UserDataClient userDataClient;
    private final JwtUtils jwtUtils;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final PermissionService permissionService;

    @Override
    public void regUser(RegistrationRequest registrationRequest) {
        registrationRequest.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        UserDataDTO userDataDTO = userMapper.toUserInfoDTO(registrationRequest);
        Set<Permission> permissions = permissionService.getDefaultUserPermissions();
        User user = userMapper.toEntity(registrationRequest);
        user.setPermissions(permissions);

        UUID userId = userService.saveUser(user);
        userDataDTO.setId(userId);
        CallbackExceptionWrapper.wrap(() -> userDataClient.createUser(userDataDTO),
                () -> userService.deleteUserById(userId));
    }

    @Override
    public LoginResponse loginUser(LoginRequest loginRequest) {
        User user = userService.findByEmail(loginRequest.getEmail());
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new AuthenticationException(ErrorCode.ERR_WRONG_CREDENTIALS);
        }
        String accessToken = jwtUtils.generateAccessToken(user);
        String refreshToken = jwtUtils.generateRefreshToken(user);
        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}

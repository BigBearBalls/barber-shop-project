package eu.senla.authservice.service.impl;

import eu.senla.authservice.client.UserDataClient;
import eu.senla.authservice.component.JwtUtils;
import eu.senla.authservice.kafka.KafkaProducer;
import eu.senla.authservice.mapper.UserMapper;
import eu.senla.authservice.model.Permission;
import eu.senla.authservice.model.User;
import eu.senla.authservice.service.AuthService;
import eu.senla.authservice.service.PermissionService;
import eu.senla.authservice.service.UserService;
import eu.senla.authservice.utility.CallbackExceptionWrapper;
import eu.senla.common.auth.dto.LoginRequest;
import eu.senla.common.auth.dto.LoginResponse;
import eu.senla.common.auth.dto.RegistrationRequest;
import eu.senla.common.dto.UserDataDTO;
import eu.senla.common.enums.ErrorCode;
import eu.senla.common.exception.AuthenticationException;
import eu.senla.common.exception.NotFoundException;
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
    private final KafkaProducer kafkaProducer;

    @Override
    public void regUser(RegistrationRequest registrationRequest) {
        registrationRequest.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        UserDataDTO userDataDTO = userMapper.toUserInfoDTO(registrationRequest);
        Set<Permission> permissions = permissionService.getDefaultUserPermissions();
        User user = userMapper.toEntity(registrationRequest);
        user.setPermissions(permissions);

        UUID userId = userService.saveUser(user);

        kafkaProducer.sendUserRegistrationEvent("user-registration", "New user registered: user@example.com");

        userDataDTO.setId(userId);
        CallbackExceptionWrapper.wrap(() -> userDataClient.createUser(userDataDTO),
                () -> userService.deleteUserById(userId));
    }

    @Override
    public LoginResponse loginUser(LoginRequest loginRequest) {
        User user;
        try {
            user = userService.findByEmail(loginRequest.getEmail());
        } catch (NotFoundException e) {
            throw new AuthenticationException(ErrorCode.ERR_WRONG_CREDENTIALS);
        }
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

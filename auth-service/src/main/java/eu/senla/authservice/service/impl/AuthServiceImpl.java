package eu.senla.authservice.service.impl;

import eu.senla.authservice.client.DepartmentUserClient;
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
import eu.senla.common.department.dto.request.CreateDepartmentUserRequest;
import eu.senla.common.dto.UserDataDTO;
import eu.senla.common.enums.ErrorCode;
import eu.senla.common.exception.ApiException;
import eu.senla.common.exception.AuthenticationException;
import eu.senla.common.exception.NotFoundException;
import eu.senla.common.kafka.dto.KafkaMailDto;
import eu.senla.common.kafka.dto.MailType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final UserDataClient userDataClient;
    private final DepartmentUserClient departmentUserClient;
    private final JwtUtils jwtUtils;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final PermissionService permissionService;
    private final KafkaProducer kafkaProducer;

    @Override
    public void regUser(RegistrationRequest registrationRequest) {
        registrationRequest.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        UserDataDTO userDataDTO = userMapper.toUserInfoDTO(registrationRequest);
        CreateDepartmentUserRequest createDepartmentUserRequest = userMapper.toDepartmentUserDTO(registrationRequest);
        Set<Permission> permissions = permissionService.getDefaultUserPermissions();
        User user = userMapper.toEntity(registrationRequest);
        user.setPermissions(permissions);

        UUID userId = userService.saveUser(user);

        userDataDTO.setId(userId);
        createDepartmentUserRequest.setId(userId);
        AtomicBoolean success = new AtomicBoolean(true);
        CallbackExceptionWrapper.wrap(() -> {
                    userDataClient.createUser(userDataDTO);
                    departmentUserClient.createUser(createDepartmentUserRequest);
        }, () -> {
            success.set(false);
            userService.deleteUserById(userId);
        });
        if (success.get()) {
            kafkaProducer.sendUserRegistrationEvent("user-registration",
                    new KafkaMailDto(MailType.REGISTRATION_MAIL, user.getEmail(), "Welcome to PLAHCTOH",
                            "Successful registration. Thank you."));
        }
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

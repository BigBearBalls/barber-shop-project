package eu.senla.authservice.service.impl;

import eu.senla.authservice.component.JwtUtils;
import eu.senla.authservice.dto.LoginRequest;
import eu.senla.authservice.dto.LoginResponse;
import eu.senla.authservice.dto.RegistrationRequest;
import eu.senla.authservice.dto.UserDTO;
import eu.senla.authservice.enums.ErrorCode;
import eu.senla.authservice.exception.AuthenticationException;
import eu.senla.authservice.service.AuthService;
import eu.senla.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtils jwtUtils;

    @Override
    public void regUser(RegistrationRequest registrationRequest) {
        registrationRequest.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        userService.regUser(registrationRequest);
    }

    @Override
    public LoginResponse loginUser(LoginRequest loginRequest) {
        UserDTO userDTO = userService.getUserByEmail(loginRequest.getEmail());
        String password = userService.getUserPasswordById(userDTO.getId());
        if (!passwordEncoder.matches(loginRequest.getPassword(), password)) {
            throw new AuthenticationException(ErrorCode.ERR_WRONG_CREDENTIALS);
        }
        String accessToken = jwtUtils.generateAccessToken(userDTO);
        String refreshToken = jwtUtils.generateRefreshToken(userDTO);
        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .userDTO(userDTO)
                .build();
    }
}

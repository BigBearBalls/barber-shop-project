package eu.senla.userservice.service.impl;

import eu.senla.userservice.dto.RegistrationRequest;
import eu.senla.userservice.dto.UserDTO;
import eu.senla.userservice.entity.Role;
import eu.senla.userservice.entity.RoleValue;
import eu.senla.userservice.entity.User;
import eu.senla.userservice.enums.ErrorCode;
import eu.senla.userservice.exception.ExistsException;
import eu.senla.userservice.exception.LogExceptionWrapper;
import eu.senla.userservice.exception.NotFoundException;
import eu.senla.userservice.mapper.UserMapper;
import eu.senla.userservice.repository.UserRepository;
import eu.senla.userservice.service.RoleService;
import eu.senla.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final UserMapper userMapper;

    @Override
    @Transactional(readOnly = true)
    public UserDTO getUserByEmail(String email) {
        return userMapper.toDTO(this.findByEmail(email));
    }

    @Override
    @Transactional
    public void createUser(RegistrationRequest registrationRequest) {
        userRepository.findByEmail(registrationRequest.getEmail()).ifPresent(e -> {
            throw LogExceptionWrapper.logErrorException(new ExistsException(
                    String.format(ErrorCode.ERR_USER_ALREADY_EXISTS.getMessage(), registrationRequest.getEmail()),
                    ErrorCode.ERR_USER_ALREADY_EXISTS));
        });
        User user = userMapper.toNewEntity(registrationRequest);
        Role role = roleService.getRoleByValue(RoleValue.ROLE_CLIENT);
        user.setRole(role);
        userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public String getUserPasswordById(UUID userId) {
        return userRepository.getUserPasswordById(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO getUserById(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> LogExceptionWrapper.logErrorException(
                new NotFoundException(String.format(ErrorCode.ERR_USER_NOT_FOUND.getMessage(), "id", userId),
                        ErrorCode.ERR_USER_NOT_FOUND)));
        return userMapper.toDTO(user);
    }

    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> LogExceptionWrapper.logErrorException(
                new NotFoundException(String.format(ErrorCode.ERR_USER_NOT_FOUND.getMessage(), "email", email),
                        ErrorCode.ERR_USER_NOT_FOUND)));
    }

    @Override
    public UserDetailsService userDetailsService() {
        return this::findByEmail;
    }
}

package eu.senla.authservice.service.impl;

import eu.senla.authservice.dto.RegistrationRequest;
import eu.senla.authservice.dto.UserPermissionsManipulationRequest;
import eu.senla.authservice.enums.ErrorCode;
import eu.senla.authservice.exception.ExistsException;
import eu.senla.authservice.exception.LogExceptionWrapper;
import eu.senla.authservice.exception.NotFoundException;
import eu.senla.authservice.mapper.UserMapper;
import eu.senla.authservice.model.Permission;
import eu.senla.authservice.model.User;
import eu.senla.authservice.repository.UserRepository;
import eu.senla.authservice.service.PermissionService;
import eu.senla.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PermissionService permissionService;

    @Override
    @Transactional
    public UUID regUser(RegistrationRequest registrationRequest) {
        if (userRepository.existsByEmail(registrationRequest.getEmail())) {
            throw LogExceptionWrapper.logErrorException(new ExistsException(String.format(
                    ErrorCode.ERR_USER_ALREADY_EXISTS.getMessage(), "email", registrationRequest.getEmail()),
                    ErrorCode.ERR_USER_ALREADY_EXISTS));
        }
        User user = userMapper.toEntity(registrationRequest);
        Set<Permission> permissions = permissionService.getDefaultUserPermissions();
        user.setPermissions(permissions);
        return userRepository.save(user).getId();
    }

    @Override
    @Transactional
    public void deleteUserById(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> LogExceptionWrapper
                .logErrorException(new NotFoundException(String.format(ErrorCode.ERR_USER_NOT_FOUND.getMessage(),
                        "email", email), ErrorCode.ERR_USER_NOT_FOUND)));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public User findById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> LogExceptionWrapper
                .logErrorException(new NotFoundException(String.format(ErrorCode.ERR_USER_NOT_FOUND.getMessage(),
                        "id", id), ErrorCode.ERR_USER_NOT_FOUND)));
    }

    @Override
    @Transactional
    public void addPermissionsToUser(UserPermissionsManipulationRequest request) {
        User user = findById(request.getUserId());
        Set<Permission> permissions = permissionService.getPermissions(request.getPermissionValues());
        user.getPermissions().addAll(permissions);
    }

    @Override
    @Transactional
    public void removeUserPermissions(UserPermissionsManipulationRequest request) {
        User user = findById(request.getUserId());
        Set<Permission> permissions = permissionService.getPermissions(request.getPermissionValues());
        user.getPermissions().removeAll(permissions);
    }

    @Override
    public UserDetailsService userDetailsService() {
        return this::findByEmail;
    }


}

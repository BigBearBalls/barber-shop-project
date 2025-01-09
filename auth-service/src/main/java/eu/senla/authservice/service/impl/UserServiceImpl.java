package eu.senla.authservice.service.impl;

import eu.senla.authservice.model.User;
import eu.senla.authservice.repository.UserRepository;
import eu.senla.authservice.service.UserService;
import eu.senla.common.auth.dto.UserCredentialsDTO;
import eu.senla.httpconfiguration.exceptioncontroller.enums.ErrorCode;
import eu.senla.httpconfiguration.exceptioncontroller.exception.ExistsException;
import eu.senla.httpconfiguration.exceptioncontroller.exception.LogExceptionWrapper;
import eu.senla.httpconfiguration.exceptioncontroller.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UUID saveUser(User user) {
        if (userRepository.existsByEmailIgnoreCase(user.getEmail())) {
            throw LogExceptionWrapper.logErrorException(new ExistsException(String.format(
                    ErrorCode.ERR_USER_ALREADY_EXISTS.getMessage(), "email", user.getEmail()),
                    ErrorCode.ERR_USER_ALREADY_EXISTS));
        }
        user.setEmail(user.getEmail().toLowerCase());
        return userRepository.save(user).getId();
    }

    @Override
    @Transactional
    public void deleteUserById(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public User findByEmail(String email) {
        return userRepository.findByEmailIgnoreCase(email).orElseThrow(() -> LogExceptionWrapper
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
    @Transactional(propagation = Propagation.SUPPORTS)
    public UserCredentialsDTO getUserCredentialsById(UUID id) {
        User user = this.findById(id);
        return new UserCredentialsDTO(user.getId(), user.getEmail());
    }
}

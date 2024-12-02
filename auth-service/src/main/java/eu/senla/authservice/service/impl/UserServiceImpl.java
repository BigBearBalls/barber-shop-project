package eu.senla.authservice.service.impl;

import eu.senla.authservice.dto.UserCredentialsDTO;
import eu.senla.authservice.enums.ErrorCode;
import eu.senla.authservice.exception.ExistsException;
import eu.senla.authservice.exception.LogExceptionWrapper;
import eu.senla.authservice.exception.NotFoundException;
import eu.senla.authservice.model.User;
import eu.senla.authservice.repository.UserRepository;
import eu.senla.authservice.service.UserService;
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
        if (userRepository.existsByEmail(user.getEmail())) {
            throw LogExceptionWrapper.logErrorException(new ExistsException(String.format(
                    ErrorCode.ERR_USER_ALREADY_EXISTS.getMessage(), "email", user.getEmail()),
                    ErrorCode.ERR_USER_ALREADY_EXISTS));
        }
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
    @Transactional(propagation = Propagation.SUPPORTS)
    public UserCredentialsDTO getUserCredentialsById(UUID id) {
        User user = this.findById(id);
        return new UserCredentialsDTO(user.getId(), user.getEmail());
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public UserCredentialsDTO getUserCredentialsByEmail(String email) {
        User user = this.findByEmail(email);
        return new UserCredentialsDTO(user.getId(), user.getEmail());
    }
}

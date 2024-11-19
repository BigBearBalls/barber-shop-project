package eu.senla.userservice.service.impl;

import eu.senla.userservice.dto.UserDataDTO;
import eu.senla.userservice.entity.User;
import eu.senla.userservice.enums.ErrorCode;
import eu.senla.userservice.exception.LogExceptionWrapper;
import eu.senla.userservice.exception.NotFoundException;
import eu.senla.userservice.mapper.UserMapper;
import eu.senla.userservice.repository.UserRepository;
import eu.senla.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public void createUser(UserDataDTO dto) {
        User user = userMapper.toEntity(dto);
        userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDataDTO getUserById(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> LogExceptionWrapper.logErrorException(
                new NotFoundException(String.format(ErrorCode.ERR_USER_NOT_FOUND.getMessage(), "id", userId),
                        ErrorCode.ERR_USER_NOT_FOUND)));
        return userMapper.toDTO(user);
    }
}

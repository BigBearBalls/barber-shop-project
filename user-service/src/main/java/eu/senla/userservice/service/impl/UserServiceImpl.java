package eu.senla.userservice.service.impl;

import eu.senla.common.enums.ErrorCode;
import eu.senla.common.exception.ExistsException;
import eu.senla.common.exception.LogExceptionWrapper;
import eu.senla.common.exception.NotFoundException;
import eu.senla.common.user.dto.UserDataDTO;
import eu.senla.userservice.entity.User;
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
        if (userRepository.existsByPhoneNumber(dto.getPhoneNumber())) {
            throw LogExceptionWrapper.logErrorException(new ExistsException(String.format(
                    ErrorCode.ERR_USER_ALREADY_EXISTS.getMessage(), "phoneNumber", dto.getPhoneNumber()),
                    ErrorCode.ERR_USER_ALREADY_EXISTS));
        }
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

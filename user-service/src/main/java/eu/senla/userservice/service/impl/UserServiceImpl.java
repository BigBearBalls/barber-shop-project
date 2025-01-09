package eu.senla.userservice.service.impl;

import eu.senla.common.account.dto.FindUsersAccountsRequest;
import eu.senla.httpconfiguration.exceptioncontroller.enums.ErrorCode;
import eu.senla.common.user.dto.UserDataDTO;
import eu.senla.common.user.dto.UsersDataResponse;
import eu.senla.httpconfiguration.exceptioncontroller.exception.ExistsException;
import eu.senla.httpconfiguration.exceptioncontroller.exception.LogExceptionWrapper;
import eu.senla.httpconfiguration.exceptioncontroller.exception.NotFoundException;
import eu.senla.userservice.entity.User;
import eu.senla.userservice.mapper.UserMapper;
import eu.senla.userservice.repository.UserRepository;
import eu.senla.userservice.service.UserService;
import eu.senla.userservice.specification.UserSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
                    ErrorCode.ERR_PHONE_NUMBER_ALREADY_EXISTS.getMessage(), "phoneNumber", dto.getPhoneNumber()),
                    ErrorCode.ERR_PHONE_NUMBER_ALREADY_EXISTS));
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

    @Override
    @Transactional(readOnly = true)
    public UsersDataResponse searchUsers(FindUsersAccountsRequest request) {
        Specification<User> specification = Specification.where(UserSpecifications.hasFirstName(request.getFirstName()))
                .and(UserSpecifications.hasLastName(request.getLastName()));
        List<User> users = userRepository.findAll(specification);
        return userMapper.toUsersDataResponse(users);
    }
}

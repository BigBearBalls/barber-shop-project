package eu.senla.userservice.service.impl;

import eu.senla.common.account.dto.AccountDetailsDTO;
import eu.senla.common.account.dto.FindUsersAccountsRequest;
import eu.senla.common.account.dto.PreviewAccountDTO;
import eu.senla.common.department.dto.response.DepartmentUserDTO;
import eu.senla.common.department.dto.response.ShortDepartmentUserInfoDTO;
import eu.senla.common.dto.UserCredentialsDTO;
import eu.senla.common.dto.UserDataDTO;
import eu.senla.common.user.dto.UsersDataResponse;
import eu.senla.httpconfiguration.security.holder.UserHolder;
import eu.senla.userservice.client.DepartmentUserClient;
import eu.senla.userservice.client.UserCredentialsClient;
import eu.senla.userservice.mapper.AccountMapper;
import eu.senla.userservice.service.AccountService;
import eu.senla.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final UserCredentialsClient userCredentialsClient;
    private final DepartmentUserClient departmentUserClient;
    private final AccountMapper accountMapper;

    private final UserService userService;

    @Override
    public AccountDetailsDTO getAccountDetails() {
        UserCredentialsDTO userDTO = userCredentialsClient.getUserCredentials();
        UUID userId = UserHolder.getUser().getId();
        UserDataDTO userDataDTO = userService.getUserById(userId);

        DepartmentUserDTO departmentUserDTO = departmentUserClient.getUser();
        return getAccountDetailsDTO(departmentUserDTO, userDTO, userDataDTO);
    }

    @Override
    public AccountDetailsDTO getAccountDetails(UUID userId) {
        UserCredentialsDTO userDTO = userCredentialsClient.getUserCredentialsById(userId);
        UserDataDTO userDataDTO = userService.getUserById(userId);
        DepartmentUserDTO departmentUserDTO = departmentUserClient.getUserById(userId);
        return getAccountDetailsDTO(departmentUserDTO, userDTO, userDataDTO);
    }

    @Override
    public PreviewAccountDTO getPreviewAccount(UUID userId) {
        UserDataDTO userDataDTO = userService.getUserById(userId);
        ShortDepartmentUserInfoDTO shortDepartmentUserInfoDTO = departmentUserClient.getShortUserInfo(userId);
        return accountMapper.toDTO(userDataDTO, shortDepartmentUserInfoDTO);
    }

    @Override
    public UsersDataResponse findUsersAccounts(FindUsersAccountsRequest request) {
        return userService.searchUsers(request);
    }

    private AccountDetailsDTO getAccountDetailsDTO(DepartmentUserDTO departmentUserDTO, UserCredentialsDTO userDTO,
                                                   UserDataDTO userDataDTO) {
        PreviewAccountDTO previewAccountDTO = null;

        if (departmentUserDTO.getTeamLeader() != null && !departmentUserDTO.getId().equals(
                departmentUserDTO.getTeamLeader().getId())) {
            UserDataDTO teamLeaderDataDTO = userService.getUserById(departmentUserDTO.getTeamLeader().getId());
            previewAccountDTO = accountMapper.toDTO(teamLeaderDataDTO, departmentUserDTO.getTeamLeader());
        }
        return accountMapper.toDTO(userDTO, userDataDTO, departmentUserDTO, previewAccountDTO);
    }
}

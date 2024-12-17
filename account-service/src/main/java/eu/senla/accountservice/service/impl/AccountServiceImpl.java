package eu.senla.accountservice.service.impl;

import eu.senla.accountservice.client.DepartmentUserClient;
import eu.senla.accountservice.client.UserCredentialsClient;
import eu.senla.accountservice.client.UserDataClient;
import eu.senla.accountservice.mapper.AccountMapper;
import eu.senla.accountservice.service.AccountService;
import eu.senla.common.account.dto.AccountDetailsDTO;
import eu.senla.common.account.dto.FindUsersAccountsRequest;
import eu.senla.common.account.dto.PreviewAccountDTO;
import eu.senla.common.account.dto.PreviewsAccountsResponse;
import eu.senla.common.department.dto.response.DepartmentUserDTO;
import eu.senla.common.department.dto.response.ShortDepartmentUserInfoDTO;
import eu.senla.common.dto.UserCredentialsDTO;
import eu.senla.common.dto.UserDataDTO;
import eu.senla.common.user.dto.UsersDataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final UserCredentialsClient userCredentialsClient;
    private final UserDataClient userDataClient;
    private final DepartmentUserClient departmentUserClient;
    private final AccountMapper accountMapper;

    @Override
    public AccountDetailsDTO getAccountDetails() {
        UserCredentialsDTO userDTO = userCredentialsClient.getUserCredentials();
        UserDataDTO userDataDTO = userDataClient.getUserData();
        DepartmentUserDTO departmentUserDTO = departmentUserClient.getUser();
        return getAccountDetailsDTO(departmentUserDTO, userDTO, userDataDTO);
    }

    @Override
    public AccountDetailsDTO getAccountDetails(UUID userId) {
        UserCredentialsDTO userDTO = userCredentialsClient.getUserCredentialsById(userId);
        UserDataDTO userDataDTO = userDataClient.getUserDataById(userId);
        DepartmentUserDTO departmentUserDTO = departmentUserClient.getUserById(userId);
        return getAccountDetailsDTO(departmentUserDTO, userDTO, userDataDTO);
    }

    @Override
    public PreviewAccountDTO getPreviewAccount(UUID userId) {
        UserDataDTO userDataDTO = userDataClient.getUserDataById(userId);
        ShortDepartmentUserInfoDTO shortDepartmentUserInfoDTO = departmentUserClient.getShortUserInfo(userId);
        return accountMapper.toDTO(userDataDTO, shortDepartmentUserInfoDTO);
    }

    @Override
    public UsersDataResponse findUsersAccounts(FindUsersAccountsRequest request) {
        return userDataClient.searchUser(request.getFirstName(), request.getLastName());
    }

    private AccountDetailsDTO getAccountDetailsDTO(DepartmentUserDTO departmentUserDTO, UserCredentialsDTO userDTO,
                                                   UserDataDTO userDataDTO) {
        PreviewAccountDTO previewAccountDTO = null;

        if (departmentUserDTO.getTeamLeader() != null && !departmentUserDTO.getId().equals(
                departmentUserDTO.getTeamLeader().getId())) {
            UserDataDTO teamLeaderDataDTO = userDataClient.getUserDataById(departmentUserDTO.getTeamLeader().getId());
            previewAccountDTO = accountMapper.toDTO(teamLeaderDataDTO, departmentUserDTO.getTeamLeader());
        }
        return accountMapper.toDTO(userDTO, userDataDTO, departmentUserDTO, previewAccountDTO);
    }
}

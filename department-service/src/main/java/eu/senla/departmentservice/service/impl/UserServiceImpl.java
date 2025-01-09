package eu.senla.departmentservice.service.impl;

import eu.senla.common.department.dto.request.CreateDepartmentUserRequest;
import eu.senla.common.department.dto.response.DepartmentUserDTO;
import eu.senla.common.department.dto.response.ShortDepartmentUserInfoDTO;
import eu.senla.common.enums.DepartmentRole;
import eu.senla.httpconfiguration.exceptioncontroller.enums.ErrorCode;
import eu.senla.departmentservice.mapper.UserMapper;
import eu.senla.departmentservice.model.Department;
import eu.senla.departmentservice.model.User;
import eu.senla.departmentservice.repository.UserRepository;
import eu.senla.departmentservice.service.UserService;
import eu.senla.httpconfiguration.exceptioncontroller.exception.LogExceptionWrapper;
import eu.senla.httpconfiguration.exceptioncontroller.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public User getUserById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> LogExceptionWrapper.logErrorException(new NotFoundException(
                String.format(ErrorCode.ERR_USER_NOT_FOUND.getMessage(), "id", id), ErrorCode.ERR_USER_NOT_FOUND)));
    }

    @Override
    @Transactional
    public DepartmentUserDTO getUserDTOById(UUID id) {
        User user = this.getUserById(id);
        DepartmentUserDTO departmentUserDTO = userMapper.toDepartmentUserDTO(user);
        if (user.getDepartment() != null && user.getDepartment().getTeamLeaderId() != null) {
            UUID teamLeaderId = user.getDepartment().getTeamLeaderId();
            User teamLeader = this.getUserById(teamLeaderId);
            ShortDepartmentUserInfoDTO departmentUserInfoDTO = userMapper.toShortDepartmentUserInfoDTO(teamLeader);
            departmentUserDTO.setTeamLeader(departmentUserInfoDTO);
        } else {
            departmentUserDTO.setTeamLeader(null);
        }
        return departmentUserDTO;
    }

    @Override
    @Transactional
    public void updateUsersDepartment(Set<UUID> userIds, Department department) {
        userRepository.updateUsersDepartment(userIds, department);
    }

    @Override
    @Transactional
    public void saveUser(CreateDepartmentUserRequest request) {
        User user = userMapper.toUser(request);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void removeUsersDepartment(Set<UUID> usersIds, Department department) {
        userRepository.removeUsersDepartment(usersIds, department);
    }

    @Override
    @Transactional
    public void setUserRole(UUID userId, DepartmentRole role) {
        User user = this.getUserById(userId);
        user.setRole(role);
    }

    @Override
    @Transactional
    public ShortDepartmentUserInfoDTO getUserShortInfo(UUID id) {
        User user = this.getUserById(id);
        return userMapper.toShortDepartmentUserInfoDTO(user);
    }
}

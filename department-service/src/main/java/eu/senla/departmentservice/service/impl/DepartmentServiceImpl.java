package eu.senla.departmentservice.service.impl;

import eu.senla.common.department.dto.request.CreateDepartmentRequest;
import eu.senla.common.department.dto.request.UpdateDepartmentRequest;
import eu.senla.common.department.dto.request.UsersIdsDTO;
import eu.senla.common.department.dto.response.DepartmentDTO;
import eu.senla.common.department.dto.response.DepartmentPageResponse;
import eu.senla.common.department.dto.response.ShortDepartmentUserInfoResponse;
import eu.senla.common.enums.ErrorCode;
import eu.senla.common.exception.ExistsException;
import eu.senla.common.exception.LogExceptionWrapper;
import eu.senla.common.exception.NotFoundException;
import eu.senla.departmentservice.mapper.DepartmentMapper;
import eu.senla.departmentservice.mapper.UserMapper;
import eu.senla.departmentservice.model.Department;
import eu.senla.departmentservice.model.User;
import eu.senla.departmentservice.repository.DepartmentRepository;
import eu.senla.departmentservice.service.DepartmentService;
import eu.senla.departmentservice.service.UserService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final UserService userService;
    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public DepartmentDTO createDepartment(CreateDepartmentRequest createDepartmentRequest) {
        Department department = departmentMapper.toDepartment(createDepartmentRequest);
        if (departmentRepository.existsByDepartmentName((department.getDepartmentName()))) {
            throw LogExceptionWrapper.logErrorException(new ExistsException(String.format(ErrorCode.ERR_DEPARTMENT_EXISTS
                    .getMessage(), "name", department.getDepartmentName()), ErrorCode.ERR_DEPARTMENT_EXISTS));
        }
        return departmentMapper.toDepartmentDTO(departmentRepository.save(department));
    }

    @Override
    @Transactional
    public void updateDepartment(UUID departmentId, UpdateDepartmentRequest request) {
        Department department = this.getDepartmentById(departmentId);
        if (request.getTeamLeaderId() != null) {
            User user = userService.getUserById(request.getTeamLeaderId());
            user.setDepartment(department);
            department.setTeamLeaderId(request.getTeamLeaderId());
        }
        if (request.getDepartmentName() != null) {
            department.setDepartmentName(request.getDepartmentName());
        }
    }

    @Override
    @Transactional
    public void addUsersToDepartment(UUID departmentId, UsersIdsDTO request) {
        Department department = this.getDepartmentById(departmentId);
        userService.updateUsersDepartment(request.getUsersIds(), department);
    }

    @Override
    @Transactional
    public void deleteDepartment(UUID departmentId) {
        departmentRepository.deleteById(departmentId);
    }

    @Override
    @Transactional
    public void removeUsersFromDepartment(UUID departmentId, UsersIdsDTO request) {
        Department department = this.getDepartmentById(departmentId);
        userService.removeUsersDepartment(request.getUsersIds(), department);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public ShortDepartmentUserInfoResponse getDepartmentUsersIds(UUID departmentId) {
        Department department = this.getDepartmentById(departmentId);
        return userMapper.toShortDepartmentUserInfoResponse(department.getUsers());
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Department getDepartmentById(UUID departmentId) {
        return departmentRepository.findById(departmentId).orElseThrow(() -> LogExceptionWrapper
                .logErrorException(new NotFoundException(String.format(ErrorCode.ERR_DEPARTMENT_NOT_FOUND.getMessage(),
                        "id", departmentId), ErrorCode.ERR_DEPARTMENT_NOT_FOUND)));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public DepartmentPageResponse getDepartmentPage(Pageable pageable) {
        Page<Department> departments = departmentRepository.findAll(pageable);
        return departmentMapper.toDepartmentPageResponse(departments.stream().toList(), departments.getTotalElements());
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public DepartmentDTO getDepartment(UUID departmentId) {
        return departmentMapper.toDepartmentDTO(this.getDepartmentById(departmentId));
    }

    @Override
    @Transactional
    public Department getDepartmentByTeamLeaderId(UUID teamLeadId) {
        Department department = departmentRepository.findByTeamLeaderId(teamLeadId).orElseThrow(() -> LogExceptionWrapper
                .logErrorException(new NotFoundException(String.format(ErrorCode.ERR_DEPARTMENT_NOT_FOUND.getMessage(),
                        "team leader id", teamLeadId), ErrorCode.ERR_DEPARTMENT_NOT_FOUND)));

        return department;
    }
}

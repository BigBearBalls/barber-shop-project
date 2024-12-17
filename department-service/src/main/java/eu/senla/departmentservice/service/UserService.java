package eu.senla.departmentservice.service;

import eu.senla.common.department.dto.request.CreateDepartmentUserRequest;
import eu.senla.common.department.dto.response.DepartmentUserDTO;
import eu.senla.common.department.dto.response.ShortDepartmentUserInfoDTO;
import eu.senla.common.enums.DepartmentRole;
import eu.senla.departmentservice.model.Department;
import eu.senla.departmentservice.model.User;

import java.util.Set;
import java.util.UUID;

public interface UserService {

    /**
     * Get user by id if exist or else throw exception
     * @param id user id
     * @return user if exist or else exception will be thrown
     */
    User getUserById(UUID id);

    /**
     * Get user dto by id if exist or else throw exception
     * @param id user id
     * @return user if exist or else exception will be thrown
     */
    DepartmentUserDTO getUserDTOById(UUID id);

    /**
     * Update users department. If set of users ids is empty then nothing happen.
     * @param usersIds ids of users to update
     * @param department department
     */
    void updateUsersDepartment(Set<UUID> usersIds, Department department);

    /**
     * Save user or update
     * @param request dto which contains data for user create operation
     */
    void saveUser(CreateDepartmentUserRequest request);

    /**
     * Remove users department. If set of users ids is empty then nothing happen.
     * @param usersIds ids of users to update
     * @param department department
     */
    void removeUsersDepartment(Set<UUID> usersIds, Department department);

    /**
     * Set role to user
     * @param userId user id
     * @param role role
     */
    void setUserRole(UUID userId, DepartmentRole role);

    ShortDepartmentUserInfoDTO getUserShortInfo(UUID id);
}

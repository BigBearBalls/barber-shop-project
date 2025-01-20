package eu.senla.departmentservice.service;

import eu.senla.common.department.dto.request.UsersIdsDTO;
import eu.senla.common.department.dto.request.UpdateDepartmentRequest;
import eu.senla.common.department.dto.response.DepartmentDTO;
import eu.senla.common.department.dto.request.CreateDepartmentRequest;
import eu.senla.common.department.dto.response.DepartmentPageResponse;
import eu.senla.common.department.dto.response.ShortDepartmentUserInfoResponse;
import eu.senla.departmentservice.model.Department;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface DepartmentService {

    /**
     * Allow to create a new department without team leader assigment
     * @param createDepartmentRequest dto with department info
     * @return department dto
     */
    DepartmentDTO createDepartment(CreateDepartmentRequest createDepartmentRequest);

    /**
     * Assign user as team leader of the department.
     *
     * @param departmentId department id
     * @param request      data for update department
     */
    void updateDepartment(UUID departmentId, UpdateDepartmentRequest request);

    /**
     * Adds users to department. If user assigned to another department then department will be overwritten
     *
     * @param departmentId department id
     * @param request      data with users ids
     */
    void addUsersToDepartment(UUID departmentId, UsersIdsDTO request);

    /**
     * Delete department by id
     * @param departmentId departmentId
     */
    void deleteDepartment(UUID departmentId);

    /**
     * Remove users from the department. If user assigned to another department then nothing happen
     * @param departmentId department id
     * @param request dto with users ids
     */
    void removeUsersFromDepartment(UUID departmentId, UsersIdsDTO request);

    /**
     * Get department users
     *
     * @param departmentId department id
     * @return dto with a list of users dto which contains users info
     */
    ShortDepartmentUserInfoResponse getDepartmentUsersIds(UUID departmentId);

    /**
     * Get department by id
     * @param departmentId department id
     * @return department entity
     */
    Department getDepartmentById(UUID departmentId);

    /**
     * Get page of departments
     *
     * @param pageable pageable element
     * @return dto with list of departments and total count departments in the database
     */
    DepartmentPageResponse getDepartmentPage(Pageable pageable);

    DepartmentDTO getDepartment(UUID departmentId);

    Department getDepartmentByTeamLeaderId(UUID teamLeadId);
}

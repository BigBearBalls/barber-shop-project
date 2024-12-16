package eu.senla.departmentservice.controller;

import eu.senla.common.department.dto.request.UsersIdsDTO;
import eu.senla.common.department.dto.request.CreateDepartmentRequest;
import eu.senla.common.department.dto.request.UpdateDepartmentRequest;
import eu.senla.common.department.dto.response.DepartmentDTO;
import eu.senla.common.department.dto.response.DepartmentPageResponse;
import eu.senla.common.department.dto.response.ShortDepartmentUserInfoResponse;
import eu.senla.departmentservice.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/departments/")
public class DepartmentController {

    private final DepartmentService departmentService;

    /**
     * Create department
     * @param createDepartmentRequest data needed for department create operation
     * @return dto with department info
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DepartmentDTO createDepartment(@RequestBody CreateDepartmentRequest createDepartmentRequest) {
        return departmentService.createDepartment(createDepartmentRequest);
    }

    /**
     * Update department by id. If dto field value present then this field will be updated. Null value will be ignored
     * @param departmentId department id
     * @param updateDepartmentRequest dto with data for update.
     */
    @PatchMapping("{departmentId}")
    public void updateDepartment(@PathVariable("departmentId") UUID departmentId,
                                 @RequestBody @Valid UpdateDepartmentRequest updateDepartmentRequest) {
        departmentService.updateDepartment(departmentId, updateDepartmentRequest);
    }

    /**
     * Delete department by id
     * @param departmentId department id
     */
    @DeleteMapping("{departmentId}")
    public void deleteDepartment(@PathVariable("departmentId") UUID departmentId) {
        departmentService.deleteDepartment(departmentId);
    }

    /**
     * Get list of department users
     * @param departmentId department id
     */
    @GetMapping("{departmentId}/users")
    public ShortDepartmentUserInfoResponse getDepartmentUsers(@PathVariable("departmentId") UUID departmentId) {
        return departmentService.getDepartmentUsersIds(departmentId);
    }

    /**
     * Adds users to department. If user assigned to another department then department will be overwritten
     * @param departmentId department id
     * @param usersIdsDTO dto with users ids
     */
    @PatchMapping("{departmentId}/users")
    public void addUsersToDepartment(@PathVariable("departmentId") UUID departmentId,
                                     @RequestBody @Valid UsersIdsDTO usersIdsDTO) {
        departmentService.addUsersToDepartment(departmentId, usersIdsDTO);
    }

    /**
     * Remove users from department. If user assigned to another department then nothing happen
     * @param departmentId department id
     * @param usersIdsDTO dto with users ids
     */
    @DeleteMapping("{departmentId}/users")
    public void removeUsersFromDepartment(@PathVariable("departmentId") UUID departmentId,
                                     @RequestBody @Valid UsersIdsDTO usersIdsDTO) {
        departmentService.removeUsersFromDepartment(departmentId, usersIdsDTO);
    }

    @GetMapping
    public DepartmentPageResponse getAllDepartments(Pageable pageable) {
        return departmentService.getDepartmentPage(pageable);
    }

    @GetMapping("{departmentId}")
    public DepartmentDTO getDepartment(@PathVariable("departmentId") UUID departmentId) {
        return departmentService.getDepartment(departmentId);
    }
}

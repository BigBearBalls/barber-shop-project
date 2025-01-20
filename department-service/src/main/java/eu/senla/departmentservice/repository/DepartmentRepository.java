package eu.senla.departmentservice.repository;

import eu.senla.departmentservice.model.Department;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, UUID> {
    boolean existsByDepartmentName(String departmentName);

    Optional<Department> findByTeamLeaderId(UUID teamLeadId);
}

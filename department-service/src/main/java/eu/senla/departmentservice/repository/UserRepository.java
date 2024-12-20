package eu.senla.departmentservice.repository;

import eu.senla.departmentservice.model.Department;
import eu.senla.departmentservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    @Modifying
    @Query(value = "update User u set u.department = :department where u.id in :userIds")
    void updateUsersDepartment(@Param("userIds") Set<UUID> userIds, @Param("department") Department department);

    @Modifying
    @Query(value = "update User u set u.department = null where u.id in :userIds and u.department = :department")
    void removeUsersDepartment(@Param("userIds") Set<UUID> userIds, @Param("department") Department department);
}

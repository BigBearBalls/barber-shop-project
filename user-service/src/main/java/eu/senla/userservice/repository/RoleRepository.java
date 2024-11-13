package eu.senla.userservice.repository;

import eu.senla.userservice.entity.Role;
import eu.senla.userservice.entity.RoleValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Short> {

    Role findByRoleValue(RoleValue value);
}

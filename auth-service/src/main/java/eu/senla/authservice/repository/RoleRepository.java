package eu.senla.authservice.repository;

import eu.senla.authservice.model.Role;
import eu.senla.authservice.model.RoleValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Short> {

    Role getRoleByRoleValue(RoleValue roleValue);
}

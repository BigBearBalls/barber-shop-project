package eu.senla.authservice.repository;

import eu.senla.authservice.enums.PermissionValue;
import eu.senla.authservice.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer> {

    Set<Permission> getPermissionsByPermissionValueIn(Set<PermissionValue> permissionValues);

    Permission getPermissionByPermissionValue(PermissionValue permissionValue);
}

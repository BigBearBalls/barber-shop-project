package eu.senla.authservice.service.impl;

import eu.senla.authservice.dto.PermissionsDTO;
import eu.senla.authservice.enums.PermissionValue;
import eu.senla.authservice.mapper.PermissionMapper;
import eu.senla.authservice.model.Permission;
import eu.senla.authservice.repository.PermissionRepository;
import eu.senla.authservice.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;
    private final PermissionMapper permissionMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Set<Permission> getDefaultUserPermissions() {
        Set<PermissionValue> permissionValues = Set.of(PermissionValue.EDIT_ACCOUNT, PermissionValue.VIEW_ACCOUNT,
                PermissionValue.CREATE_BOOKING, PermissionValue.VIEW_PROCEDURE, PermissionValue.VIEW_SELF_BOOKINGS);
        return this.getPermissions(permissionValues);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Set<Permission> getPermissions(Set<PermissionValue> permissionValues) {
        return permissionRepository.getPermissionsByPermissionValueIn(permissionValues);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Permission getPermission(PermissionValue permissionValue) {
        return permissionRepository.getPermissionByPermissionValue(permissionValue);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public PermissionsDTO getPermissions() {
        Set<Permission> set = new HashSet<>(permissionRepository.findAll());
        return permissionMapper.toDTO(set);
    }
}

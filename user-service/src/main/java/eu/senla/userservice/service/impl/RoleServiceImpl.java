package eu.senla.userservice.service.impl;

import eu.senla.userservice.entity.Role;
import eu.senla.userservice.entity.RoleValue;
import eu.senla.userservice.repository.RoleRepository;
import eu.senla.userservice.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public Role getRoleByValue(RoleValue roleValue) {
        return roleRepository.findByRoleValue(roleValue);
    }
}

package eu.senla.authservice.service.impl;

import eu.senla.authservice.model.Role;
import eu.senla.authservice.model.RoleValue;
import eu.senla.authservice.repository.RoleRepository;
import eu.senla.authservice.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role getRoleByValue(RoleValue value) {
        return roleRepository.getRoleByRoleValue(value);
    }
}

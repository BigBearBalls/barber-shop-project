package eu.senla.authservice.service;

import eu.senla.authservice.model.Role;
import eu.senla.authservice.model.RoleValue;

public interface RoleService {

    Role getRoleByValue(RoleValue value);
}

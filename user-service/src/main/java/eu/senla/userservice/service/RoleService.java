package eu.senla.userservice.service;

import eu.senla.userservice.entity.Role;
import eu.senla.userservice.entity.RoleValue;

public interface RoleService {

    Role getRoleByValue(RoleValue roleValue);
}

package eu.senla.authservice.aspect;

import eu.senla.authservice.annotation.CheckPermission;
import eu.senla.authservice.enums.ErrorCode;
import eu.senla.authservice.enums.PermissionValue;
import eu.senla.authservice.exception.AuthorizationException;
import eu.senla.authservice.model.Permission;
import eu.senla.authservice.model.User;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Aspect
@Component
public class CheckPermissionAspect {

    @Before("@annotation(checkPermission)")
    public void checkPermission(CheckPermission checkPermission) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Set<PermissionValue> set = user.getPermissions().stream().map(Permission::getPermissionValue)
                .collect(Collectors.toSet());
        if (!set.contains(checkPermission.value())) {
            throw new AuthorizationException(ErrorCode.ERR_ACCESS_DENIED);
        }
    }
}

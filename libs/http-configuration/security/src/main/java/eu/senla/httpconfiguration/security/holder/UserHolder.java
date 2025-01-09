package eu.senla.httpconfiguration.security.holder;

import eu.senla.httpconfiguration.exceptioncontroller.enums.ErrorCode;
import eu.senla.httpconfiguration.exceptioncontroller.exception.AuthenticationException;
import eu.senla.httpconfiguration.exceptioncontroller.exception.LogExceptionWrapper;
import eu.senla.httpconfiguration.security.dto.UserDTO;

import java.util.Optional;

public abstract class UserHolder {

    private static final ThreadLocal<UserDTO> userContext = new ThreadLocal<>();

    private UserHolder() {

    }

    public static UserDTO getUser() {
        return Optional.ofNullable(userContext.get()).orElseThrow(() -> LogExceptionWrapper.logErrorException(
                new AuthenticationException(ErrorCode.ERR_USER_UNAUTHORIZED)));
    }

    public static boolean userExists() {
        return userContext.get() != null;
    }

    public static void setUser(UserDTO dto) {
        if (dto != null) {
            userContext.set(dto);
        }
    }

    public static void removeUserId() {
        userContext.remove();
    }
}

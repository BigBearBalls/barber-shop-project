package eu.senla.httpconfiguration.security.holder;

import java.util.UUID;

public abstract class UserIdHolder {

    private static final ThreadLocal<UUID> userIdContext = new ThreadLocal<>();

    private UserIdHolder() {

    }

    public static UUID getUserId() {
        return userIdContext.get();
    }

    public static void setUserId(UUID id) {
        if (id != null) {
            userIdContext.set(id);
        }
    }

    public static void removeUserId() {
        userIdContext.remove();
    }
}

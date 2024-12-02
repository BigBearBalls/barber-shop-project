package eu.senla.httpconfiguration.holder;

import java.util.UUID;

public abstract class UserIdHolder {

    private UserIdHolder() {

    }

    private static final ThreadLocal<UUID> userIdContext = new ThreadLocal<>();

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

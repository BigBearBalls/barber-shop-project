package eu.senla.gatewayservice.component;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import eu.senla.gatewayservice.model.User;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class TrustedTokenManager {

    private Cache<String, User> trustedTokenCache;

    @Value("${spring.application.security.jwt.access-key.expiration-time}")
    private Integer jwtAccessExpiration;

    @PostConstruct
    public void init() {
        trustedTokenCache = CacheBuilder.newBuilder()
                .expireAfterWrite(jwtAccessExpiration, TimeUnit.MINUTES)
                .build();
    }

    public void saveToken(String token, User user) {
        trustedTokenCache.put(token, user);
    }

    public User getUserByToken(String token) {
        return trustedTokenCache.getIfPresent(token);
    }

    public void removeToken(String token) {
        trustedTokenCache.invalidate(token);
    }

    public boolean isTrustedToken(String token) {
        return trustedTokenCache.getIfPresent(token) != null;
    }
}

package eu.senla.httpconfiguration.security.filter;

import eu.senla.httpconfiguration.security.configuration.SecurityFiltersConfiguration;
import eu.senla.httpconfiguration.security.holder.UserIdHolder;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Component
@ConditionalOnProperty(prefix = SecurityFiltersConfiguration.PREFIX, name = "get-user-id-header-filter.enable",
        havingValue = "true")
public class UserIdHeaderConsumerFilter extends OncePerRequestFilter {

    private final List<String> ignoreUrls = List.of("/api/v1/auth/login", "/api/v1/auth/registration",
            "/actuator/health");


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (request.getHeader("X-User-Id") == null) {
            filterChain.doFilter(request, response);
            return;
        }
        UUID userId = UUID.fromString(request.getHeader("X-User-Id"));
        UserIdHolder.setUserId(userId);
        try {
            filterChain.doFilter(request, response);
        } finally {
            UserIdHolder.removeUserId();
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String uri = request.getRequestURI();
        return ignoreUrls.contains(uri);
    }
}

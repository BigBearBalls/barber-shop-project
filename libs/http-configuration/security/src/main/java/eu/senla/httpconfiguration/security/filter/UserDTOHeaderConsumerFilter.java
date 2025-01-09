package eu.senla.httpconfiguration.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.httpconfiguration.security.dto.UserDTO;
import eu.senla.httpconfiguration.security.configuration.SecurityFiltersConfiguration;
import eu.senla.httpconfiguration.security.holder.UserHolder;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@ConditionalOnProperty(prefix = SecurityFiltersConfiguration.PREFIX, name = "get-user-header-filter.enable",
        havingValue = "true")
@RequiredArgsConstructor
public class UserDTOHeaderConsumerFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    private final List<String> ignoreUrls = List.of("/api/v1/auth/login", "/api/v1/auth/registration",
            "/actuator/health");


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (request.getHeader("X-User") == null) {
            filterChain.doFilter(request, response);
            return;
        }
        UserDTO userDTO = objectMapper.readValue(request.getHeader("X-User"), UserDTO.class);
        UserHolder.setUser(userDTO);
        try {
            filterChain.doFilter(request, response);
        } finally {
            UserHolder.removeUserId();
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String uri = request.getRequestURI();
        return ignoreUrls.contains(uri);
    }
}

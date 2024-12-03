package eu.senla.gatewayservice.filters;

import eu.senla.gatewayservice.model.User;
import eu.senla.httpconfiguration.security.holder.UserIdHolder;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SecurityContextUserIdHandlerFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            UUID userId = ((User) authentication.getPrincipal()).getId();
            UserIdHolder.setUserId(userId);
            try {
                filterChain.doFilter(request, response);
            } finally {
                UserIdHolder.removeUserId();
            }
            return;
        }
        filterChain.doFilter(request, response);
    }
}

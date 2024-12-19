package eu.senla.gatewayservice.filters;

import eu.senla.httpconfiguration.security.dto.UserDTO;
import eu.senla.gatewayservice.model.User;
import eu.senla.httpconfiguration.security.holder.UserHolder;
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

@Component
@RequiredArgsConstructor
public class SecurityContextUserIdHandlerFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            User user = ((User) authentication.getPrincipal());
            UserDTO userDTO = new UserDTO(user.getId(), user.getEmail(), user.getRole());
            UserHolder.setUser(userDTO);
            try {
                filterChain.doFilter(request, response);
            } finally {
                UserHolder.removeUserId();
            }
            return;
        }
        filterChain.doFilter(request, response);
    }
}

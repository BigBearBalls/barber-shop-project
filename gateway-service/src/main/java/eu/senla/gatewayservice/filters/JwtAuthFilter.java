package eu.senla.gatewayservice.filters;

import eu.senla.gatewayservice.client.AuthClient;
import eu.senla.gatewayservice.component.TrustedTokenManager;
import eu.senla.gatewayservice.constant.SecurityConstants;
import eu.senla.gatewayservice.dto.AccessTokenExtractedData;
import eu.senla.gatewayservice.enums.ErrorCode;
import eu.senla.gatewayservice.exception.JwtValidateException;
import eu.senla.gatewayservice.model.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {

    private final AuthClient authClient;

    private final TrustedTokenManager tokenManager;

    private final List<String> ignoreUrls = List.of("/api/v1/auth/login", "/api/v1/auth/registration",
            "/actuator/health");

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader(SecurityConstants.TOKEN_HEADER);
        if (!StringUtils.hasLength(authorizationHeader) || !authorizationHeader.startsWith(SecurityConstants.TOKEN_PATTERN)) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = authorizationHeader.substring(SecurityConstants.TOKEN_PATTERN.length());
        if (!tokenManager.isTrustedToken(token)) {
            User user = getUserFromToken(token);
            tokenManager.saveToken(token, user);
        }
        User user = tokenManager.getUserByToken(token);
        String email = user.getEmail();
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    user, null, user.getAuthorities()
            );

            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            context.setAuthentication(authToken);
            SecurityContextHolder.setContext(context);
        } else {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!authentication.getPrincipal().equals(email)) {
                throw new JwtValidateException(String.format(SecurityConstants.TOKEN_WAS_STOLEN_LOG_MESSAGE, email,
                        authentication.getPrincipal()), ErrorCode.ERR_JWT_VALIDATION_EXCEPTION);
            }
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        for (String ignoreUrl : ignoreUrls) {
            if (path.contains(ignoreUrl)) {
                return true;
            }
        }
        return false;
    }

    private User getUserFromToken(String token) {
        AccessTokenExtractedData accessTokenExtractedData = authClient.getAccessTokenExtractedData(token);
        return User.builder()
                .id(accessTokenExtractedData.getUserId())
                .email(accessTokenExtractedData.getEmail())
                .permissions(accessTokenExtractedData.getPermissions())
                .build();
    }
}

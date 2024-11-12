package eu.senla.authservice.filters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.authservice.component.JwtUtils;
import eu.senla.authservice.constant.Constants;
import eu.senla.authservice.dto.exception.ExceptionResponse;
import eu.senla.authservice.enums.ErrorCode;
import eu.senla.authservice.exception.ApiException;
import eu.senla.authservice.exception.JwtValidateException;
import eu.senla.authservice.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;

    private final UserService userService;

    private final List<String> ignoreUrls = List.of("/api/v1/auth/login", "/api/v1/auth/registration");

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        try {
            String authorizationHeader = request.getHeader(Constants.TOKEN_HEADER);
            if (!StringUtils.hasLength(authorizationHeader) || !authorizationHeader.startsWith(Constants.TOKEN_PATTERN)) {
                filterChain.doFilter(request, response);
                return;
            }

            String token = authorizationHeader.substring(Constants.TOKEN_PATTERN.length());
            jwtUtils.validateAccessToken(token);
            String email = jwtUtils.getAccessClaims(token).getSubject();
            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userService.userDetailsService().loadUserByUsername(email);
                SecurityContext context = SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                context.setAuthentication(authToken);
                SecurityContextHolder.setContext(context);
            } else {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                if (!authentication.getPrincipal().equals(email)) {
                    throw new JwtValidateException(String.format(Constants.TOKEN_WAS_STOLEN_LOG_MESSAGE,  email,
                            authentication.getPrincipal()), ErrorCode.ERR_JWT_VALIDATION_EXCEPTION);
                }
            }
            filterChain.doFilter(request, response);
        } catch (JwtValidateException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().print(convertExceptionResponseToString(e, request.getRequestURI()));
            response.getWriter().flush();
        }
    }

    private String convertExceptionResponseToString(ApiException e, String path) throws JsonProcessingException {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                LocalDateTime.now(),
                ErrorCode.ERR_JWT_VALIDATION_EXCEPTION,
                e.getMessage(),
                path
        );
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(exceptionResponse);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        for (String ignoreUrl : ignoreUrls) {
            if (path.contains(ignoreUrl)) {
                return true;
            }
        }
        return false;
    }
}

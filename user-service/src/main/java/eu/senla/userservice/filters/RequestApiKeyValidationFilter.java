package eu.senla.userservice.filters;

import eu.senla.userservice.constants.SecurityConstants;
import eu.senla.userservice.enums.ErrorCode;
import eu.senla.userservice.exception.HeadersParseException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class RequestApiKeyValidationFilter extends OncePerRequestFilter {

    @Value("${spring.application.security.api-key}")
    private String apiKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String key = request.getHeader(SecurityConstants.API_KEY_HEADER);
        if (key == null || !key.equals(apiKey)) {
            throw new HeadersParseException(ErrorCode.ERR_HEADER_NOT_EXIST_OR_WRONG_VALUE);
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getRequestURI().startsWith("/actuator/");
    }
}

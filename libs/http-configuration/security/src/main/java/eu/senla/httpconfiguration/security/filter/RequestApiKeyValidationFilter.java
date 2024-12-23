package eu.senla.httpconfiguration.security.filter;

import eu.senla.common.constant.SecurityConstants;
import eu.senla.common.enums.ErrorCode;
import eu.senla.httpconfiguration.exceptioncontroller.exception.HeadersParseException;
import eu.senla.httpconfiguration.security.configuration.SecurityFiltersConfiguration;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@ConditionalOnProperty(prefix = SecurityFiltersConfiguration.PREFIX, name = "api-key-header-filter.enable",
        havingValue = "true")
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

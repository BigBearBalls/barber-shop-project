package eu.senla.gatewayservice.configuration;

import eu.senla.common.enums.ErrorCode;
import eu.senla.common.enums.PermissionValue;
import eu.senla.common.exception.AuthenticationException;
import eu.senla.gatewayservice.filters.JwtAuthFilter;
import eu.senla.gatewayservice.filters.SecurityContextUserIdHandlerFilter;
import eu.senla.gatewayservice.handler.CustomAccessDeniedHandler;
import eu.senla.gatewayservice.model.Permission;
import eu.senla.gatewayservice.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthFilter jwtAuthFilter;

    private final SecurityContextUserIdHandlerFilter securityContextUserIdHandlerFilter;

    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(request -> {
                    var corsConfiguration = new CorsConfiguration();
                    corsConfiguration.setAllowedOriginPatterns(List.of("*"));
                    corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                    corsConfiguration.setAllowedHeaders(List.of("*"));
                    corsConfiguration.setAllowCredentials(true);
                    return corsConfiguration;
                }))
                .authorizeHttpRequests(authorize -> {
                    configureAuthServiceUrl(authorize);
                    authorize.anyRequest().permitAll();
                })
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .anonymous(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .exceptionHandling(exception ->
                        exception.accessDeniedHandler(customAccessDeniedHandler))
                .addFilterAfter(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(securityContextUserIdHandlerFilter, JwtAuthFilter.class)
                .build();
    }

    private void configureAuthServiceUrl(AuthorizeHttpRequestsConfigurer<HttpSecurity>
                                                 .AuthorizationManagerRequestMatcherRegistry authorize) {
        applyPermission(authorize, HttpMethod.GET, "/api/v1/permissions/", PermissionValue.VIEW_PERMISSIONS);
        applyPermission(authorize, HttpMethod.POST, "/api/v1/permissions/user", PermissionValue.ADD_PERMISSION);
        applyPermission(authorize, HttpMethod.DELETE, "/api/v1/permissions/user", PermissionValue.REMOVE_PERMISSION);
        applyPermission(authorize, HttpMethod.GET, "/api/v1/permissions/user", PermissionValue.VIEW_SELF_PERMISSIONS);
        applyPermission(authorize, HttpMethod.GET, "/api/v1/account/", PermissionValue.VIEW_ACCOUNT);
    }

    private void applyPermission(AuthorizeHttpRequestsConfigurer<HttpSecurity>
                                         .AuthorizationManagerRequestMatcherRegistry authorize,
                                 HttpMethod method,
                                 String url,
                                 PermissionValue requiredPermission) {
        authorize.requestMatchers(method, url).access(hasPermission(requiredPermission));
    }

    private AuthorizationManager<RequestAuthorizationContext> hasPermission(PermissionValue requiredPermission) {
        return (authentication, context) -> {
            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                throw new AuthenticationException(ErrorCode.ERR_DONT_AUTHENTICATED);
            }
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Set<PermissionValue> set = user.getPermissions().stream().map(Permission::getPermissionValue)
                    .collect(Collectors.toSet());
            boolean hasPermission = checkIfPermissionExists(set, requiredPermission);
            return new AuthorizationDecision(hasPermission);
        };
    }

    private boolean checkIfPermissionExists(Set<PermissionValue> permissionValues, PermissionValue requiredPermission) {
        return permissionValues.contains(requiredPermission);
    }
}

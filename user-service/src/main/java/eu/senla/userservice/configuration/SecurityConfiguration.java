package eu.senla.userservice.configuration;

import eu.senla.userservice.filters.FiltersExceptionHandler;
import eu.senla.userservice.filters.JwtAuthFilter;
import eu.senla.userservice.filters.RequestApiKeyValidationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthFilter jwtAuthFilter;
    private final FiltersExceptionHandler filtersExceptionHandler;
    private final RequestApiKeyValidationFilter requestApiKeyValidationFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/v1/user/**").permitAll()
                        .requestMatchers("/actuator/**").permitAll()
                        .anyRequest().denyAll())
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .addFilterBefore(filtersExceptionHandler, UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(requestApiKeyValidationFilter, FiltersExceptionHandler.class)
                .addFilterAfter(jwtAuthFilter, RequestApiKeyValidationFilter.class)
                .build();
    }
}

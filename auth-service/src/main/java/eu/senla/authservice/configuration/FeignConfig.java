package eu.senla.authservice.configuration;

import eu.senla.authservice.constant.SecurityConstants;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
public class FeignConfig {

    @Value("${spring.application.security.api-key}")
    private String apiKey;

    @Bean
    public RequestInterceptor requestInterceptor() {
        SecurityContext context = SecurityContextHolder.getContext();
        System.out.println(context.getAuthentication());
        return template -> template
                .header(SecurityConstants.API_KEY_HEADER, apiKey)
                .header(SecurityConstants.REQUEST_SOURCE_HEADER, SecurityConstants.INTERNAL_REQUEST_SOURCE);
    }
}

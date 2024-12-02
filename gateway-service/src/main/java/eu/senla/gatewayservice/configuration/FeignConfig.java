package eu.senla.gatewayservice.configuration;

import eu.senla.gatewayservice.constant.SecurityConstants;
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


}

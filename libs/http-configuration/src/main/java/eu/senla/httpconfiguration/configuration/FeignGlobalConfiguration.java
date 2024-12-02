package eu.senla.httpconfiguration.configuration;

import eu.senla.httpconfiguration.factory.CustomYamlPropertySourceFactory;
import eu.senla.httpconfiguration.holder.UserIdHolder;
import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import java.util.UUID;

@Slf4j
@Configuration
@ConditionalOnProperty(prefix = FeignGlobalConfiguration.PREFIX, name = "enable", havingValue = "true")
@PropertySource(value = "classpath:application-http-configuration.yaml", factory = CustomYamlPropertySourceFactory.class)
@Import(PropertiesConfiguration.class)
public class FeignGlobalConfiguration {

    public static final String PREFIX = "http-configuration.feign";

    @Value("${spring.application.security.api-key}")
    private String apiKey;

    @Bean
    @ConditionalOnProperty(prefix = FeignGlobalConfiguration.PREFIX, name = "interceptor.add-user-id-to-headers.enable",
            havingValue = "true")
    public RequestInterceptor userIdRequestInterceptor() {
        return requestTemplate -> {
            System.out.println("asda");
            UUID userId = UserIdHolder.getUserId();
            if (userId != null) {
                requestTemplate.header("X-User-Id", userId.toString());
            }
            requestTemplate
                    .header(SecurityConstants.API_KEY_HEADER, apiKey)
                    .header(SecurityConstants.REQUEST_SOURCE_HEADER, SecurityConstants.INTERNAL_REQUEST_SOURCE);
        };
    }
}

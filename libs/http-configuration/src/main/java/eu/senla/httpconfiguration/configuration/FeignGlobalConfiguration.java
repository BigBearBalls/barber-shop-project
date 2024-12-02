package eu.senla.httpconfiguration.configuration;

import eu.senla.httpconfiguration.filter.UserIdHeaderConsumerFilter;
import eu.senla.httpconfiguration.holder.UserIdHolder;
import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Slf4j
@Configuration
@ConditionalOnProperty(prefix = FeignGlobalConfiguration.PREFIX, name = "enable", havingValue = "true")
public class FeignGlobalConfiguration {

    public static final String PREFIX = "http-configuration.feign";

    @Value("${spring.application.security.api-key}")
    private String apiKey;

    @Bean
    @ConditionalOnProperty(prefix = FeignGlobalConfiguration.PREFIX, name = "interceptor.add-user-id-to-headers.enable",
            havingValue = "true")
    public RequestInterceptor userIdRequestInterceptor() {
        return requestTemplate -> {
            UUID userId = UserIdHolder.getUserId();
            if (userId != null) {
                requestTemplate.header("X-User-Id", userId.toString());
            }
            System.out.println("Api-key: " + apiKey);
            System.out.println("User id: " + userId);
            requestTemplate.header(SecurityConstants.API_KEY_HEADER, "U3Ryb25nUGFzc3dvcmRWZXJ5U3Ryb25nWWVzWWVz")
                    .header(SecurityConstants.REQUEST_SOURCE_HEADER, SecurityConstants.INTERNAL_REQUEST_SOURCE);
        };
    }
}

package eu.senla.httpconfiguration.security.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.common.constant.SecurityConstants;
import eu.senla.httpconfiguration.exceptioncontroller.exception.ApiException;
import eu.senla.httpconfiguration.security.dto.UserDTO;
import eu.senla.httpconfiguration.security.holder.UserHolder;
import eu.senla.libs.common.serialization.configuration.ObjectMapperConfiguration;
import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@AutoConfiguration
@ConditionalOnProperty(prefix = SecurityFeignConfiguration.PREFIX, name = "enable", havingValue = "true",
        matchIfMissing = true)
@RequiredArgsConstructor
@Import({ObjectMapperConfiguration.class})
public class SecurityFeignConfiguration {

    public static final String PREFIX = "http-configuration.security.feign";
    public final ObjectMapper objectMapper;

    @Value("${spring.application.security.api-key}")
    private String apiKey;

    @Bean
    @ConditionalOnProperty(prefix = SecurityFeignConfiguration.PREFIX, name = "interceptor.add-user-to-headers.enable",
            havingValue = "true")
    public RequestInterceptor userIdRequestInterceptor() {
        return requestTemplate -> {
            if (UserHolder.userExists()) {
                UserDTO userDTO = UserHolder.getUser();
                try {
                    requestTemplate.header("X-User", objectMapper.writeValueAsString(userDTO));
                } catch (JsonProcessingException e) {
                    throw new ApiException(e.getMessage());
                }
            }
            requestTemplate
                    .header(SecurityConstants.API_KEY_HEADER, apiKey)
                    .header(SecurityConstants.REQUEST_SOURCE_HEADER, SecurityConstants.INTERNAL_REQUEST_SOURCE);
        };
    }

}

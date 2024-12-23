package eu.senla.httpconfiguration.feign.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.libs.common.serialization.configuration.ObjectMapperConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Import;

@AutoConfiguration
@Import({ObjectMapperConfiguration.class})
@ConditionalOnProperty(prefix = FeignErrorDecoderConfiguration.PREFIX, name = "enable", havingValue = "true",
        matchIfMissing = true)
@RequiredArgsConstructor
public class FeignErrorDecoderConfiguration {

    public static final String PREFIX = "http-configuration.feign.decoder";

    private final ObjectMapper objectMapper;



}

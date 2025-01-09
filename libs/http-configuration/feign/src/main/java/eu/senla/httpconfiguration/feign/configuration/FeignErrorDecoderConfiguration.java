package eu.senla.httpconfiguration.feign.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.httpconfiguration.feign.decoder.FeignCustomErrorDecoder;
import eu.senla.libs.common.serialization.configuration.ObjectMapperConfiguration;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@AutoConfiguration
@Import({ObjectMapperConfiguration.class})
@ConditionalOnProperty(prefix = FeignErrorDecoderConfiguration.PREFIX, name = "enable", havingValue = "true",
        matchIfMissing = true)
@RequiredArgsConstructor
public class FeignErrorDecoderConfiguration {

    public static final String PREFIX = "http-configuration.feign.decoder";

    private final ObjectMapper objectMapper;

    @Bean
    public ErrorDecoder errorDecoder() {
        return new FeignCustomErrorDecoder(objectMapper);
    }
}

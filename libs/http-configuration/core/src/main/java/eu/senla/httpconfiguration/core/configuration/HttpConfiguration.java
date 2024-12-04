package eu.senla.httpconfiguration.core.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ObjectMapperConfiguration.class, FiltersConfiguration.class, ExceptionHandlerConfiguration.class})
public class HttpConfiguration {
}

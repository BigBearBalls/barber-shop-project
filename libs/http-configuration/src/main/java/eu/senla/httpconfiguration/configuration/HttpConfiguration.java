package eu.senla.httpconfiguration.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Import({FeignGlobalConfiguration.class, FiltersConfiguration.class})
@PropertySource("classpath:application.yaml")
public class HttpConfiguration {
}

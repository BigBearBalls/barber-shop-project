package eu.senla.httpconfiguration.security.configuration;

import eu.senla.httpconfiguration.core.factory.CustomYamlPropertySourceFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Import({FeignGlobalConfiguration.class, SecurityFiltersConfiguration.class})
@PropertySource(value = "classpath:application-http-configuration-security.yaml", factory = CustomYamlPropertySourceFactory.class)
public class SecurityHttpConfiguration {
}

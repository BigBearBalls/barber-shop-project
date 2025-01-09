package eu.senla.httpconfiguration.security.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({SecurityFiltersConfiguration.class, SecurityFiltersConfiguration.class})
public class SecurityHttpConfiguration {
}

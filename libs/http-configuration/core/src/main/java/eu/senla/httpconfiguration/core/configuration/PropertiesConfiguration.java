package eu.senla.httpconfiguration.core.configuration;

import eu.senla.httpconfiguration.core.factory.CustomYamlPropertySourceFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:application-http-configuration-core.yaml", factory = CustomYamlPropertySourceFactory.class)
public class PropertiesConfiguration {
}

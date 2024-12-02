package eu.senla.httpconfiguration.configuration;

import eu.senla.httpconfiguration.factory.CustomYamlPropertySourceFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:application-http-configuration.yaml", factory = CustomYamlPropertySourceFactory.class)
public class PropertiesConfiguration {
}

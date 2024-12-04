package eu.senla.httpconfiguration.core.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "eu.senla.httpconfiguration.core.exception.handler")
public class ExceptionHandlerConfiguration {
}

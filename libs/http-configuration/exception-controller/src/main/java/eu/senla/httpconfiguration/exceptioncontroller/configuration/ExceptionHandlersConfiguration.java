package eu.senla.httpconfiguration.exceptioncontroller.configuration;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;

@AutoConfiguration
@ConditionalOnProperty(prefix = ExceptionHandlersConfiguration.PREFIX, name = "enable", havingValue = "true",
        matchIfMissing = true)
@ComponentScan(basePackages = "eu.senla.httpconfiguration.exceptioncontroller.exception.handler")
public class ExceptionHandlersConfiguration {

    public final static String PREFIX = "http-configuration.exception-controller.handlers";
}

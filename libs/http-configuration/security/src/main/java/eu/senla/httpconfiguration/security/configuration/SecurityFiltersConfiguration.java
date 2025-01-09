package eu.senla.httpconfiguration.security.configuration;

import eu.senla.httpconfiguration.exceptioncontroller.configuration.FiltersConfiguration;
import eu.senla.httpconfiguration.security.filter.RequestApiKeyValidationFilter;
import eu.senla.httpconfiguration.security.filter.UserDTOHeaderConsumerFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.*;
import org.springframework.core.Ordered;

import java.util.List;

@AutoConfiguration
@ConditionalOnProperty(prefix = SecurityFiltersConfiguration.PREFIX, name = "enable", havingValue = "true")
@ComponentScan(basePackages = "eu.senla.httpconfiguration.security.filter")
public class SecurityFiltersConfiguration extends FiltersConfiguration {

    public final static String PREFIX = "http-configuration.security.filters";

    @Bean
    @ConditionalOnProperty(prefix = SecurityFiltersConfiguration.PREFIX, name = "get-user-id-header-filter.enable",
            havingValue = "true")
    public FilterRegistrationBean<UserDTOHeaderConsumerFilter> userIdHeaderFilterRegistrationBean(
            @Autowired UserDTOHeaderConsumerFilter filter) {
        return filterRegistrationBean(filter, List.of("/*"), Ordered.HIGHEST_PRECEDENCE + 2);
    }

    @Bean
    @ConditionalOnProperty(prefix = SecurityFiltersConfiguration.PREFIX, name = "api-key-header-filter.enable",
            havingValue = "true")
    public FilterRegistrationBean<RequestApiKeyValidationFilter> requestApiKeyValidationFilterRegistrationBean(
            @Autowired RequestApiKeyValidationFilter filter) {
        return filterRegistrationBean(filter, List.of("/*"), Ordered.HIGHEST_PRECEDENCE + 1);
    }
}


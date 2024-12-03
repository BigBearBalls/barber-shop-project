package eu.senla.httpconfiguration.security.configuration;

import eu.senla.httpconfiguration.core.configuration.FiltersConfiguration;
import eu.senla.httpconfiguration.security.filter.RequestApiKeyValidationFilter;
import eu.senla.httpconfiguration.security.filter.UserIdHeaderConsumerFilter;
import eu.senla.httpconfiguration.core.factory.CustomYamlPropertySourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.*;
import org.springframework.core.Ordered;

import java.util.List;

@Configuration
@ConditionalOnProperty(prefix = SecurityFiltersConfiguration.PREFIX, name = "enable", havingValue = "true")
@PropertySource(value = "classpath:application-http-configuration-security.yaml", factory = CustomYamlPropertySourceFactory.class)
@ComponentScan(basePackages = "eu.senla.httpconfiguration.security.filter")
@Import({PropertiesConfiguration.class})
public class SecurityFiltersConfiguration extends FiltersConfiguration {

    public final static String PREFIX = "http-configuration.security.filters";

    @Bean
    @ConditionalOnProperty(prefix = SecurityFiltersConfiguration.PREFIX, name = "get-user-id-header-filter.enable",
            havingValue = "true")
    public FilterRegistrationBean<UserIdHeaderConsumerFilter> userIdHeaderFilterRegistrationBean(
            @Autowired UserIdHeaderConsumerFilter filter) {
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


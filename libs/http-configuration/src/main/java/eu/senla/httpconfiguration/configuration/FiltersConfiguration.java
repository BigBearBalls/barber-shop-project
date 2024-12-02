package eu.senla.httpconfiguration.configuration;

import eu.senla.httpconfiguration.factory.CustomYamlPropertySourceFactory;
import eu.senla.httpconfiguration.filter.FiltersExceptionHandler;
import eu.senla.httpconfiguration.filter.RequestApiKeyValidationFilter;
import eu.senla.httpconfiguration.filter.UserIdHeaderConsumerFilter;
import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.*;
import org.springframework.core.Ordered;

import java.util.List;

@Configuration
@ConditionalOnProperty(prefix = FiltersConfiguration.PREFIX, name = "enable", havingValue = "true")
@PropertySource(value = "classpath:application-http-configuration.yaml", factory = CustomYamlPropertySourceFactory.class)
@ComponentScan(basePackages = "eu.senla.httpconfiguration.filter")
@Import(PropertiesConfiguration.class)
public class FiltersConfiguration {

    public final static String PREFIX = "http-configuration.filters";

    @Bean
    @ConditionalOnProperty(prefix = FiltersConfiguration.PREFIX, name = "get-user-id-header-filter.enable",
            havingValue = "true")
    public FilterRegistrationBean<UserIdHeaderConsumerFilter> userIdHeaderFilterRegistrationBean(
            @Autowired UserIdHeaderConsumerFilter filter) {
        return filterRegistrationBean(filter, List.of("/*"), Ordered.HIGHEST_PRECEDENCE + 2);
    }

    @Bean
    @ConditionalOnProperty(prefix = FiltersConfiguration.PREFIX, name = "api-key-header-filter.enable",
            havingValue = "true")
    public FilterRegistrationBean<RequestApiKeyValidationFilter> requestApiKeyValidationFilterRegistrationBean(
            @Autowired RequestApiKeyValidationFilter filter) {
        return filterRegistrationBean(filter, List.of("/*"), Ordered.HIGHEST_PRECEDENCE + 1);
    }

    @Bean
    public FilterRegistrationBean<FiltersExceptionHandler> filtersExceptionHandlerRegistrationBean(
            @Autowired FiltersExceptionHandler handler) {
        return filterRegistrationBean(handler, List.of("/*"), Ordered.HIGHEST_PRECEDENCE);
    }

    private <T extends Filter> FilterRegistrationBean<T> filterRegistrationBean(T filter, List<String> urls, int order) {
        FilterRegistrationBean<T> bean = new FilterRegistrationBean<>();
        bean.setFilter(filter);
        bean.setUrlPatterns(urls);
        bean.setOrder(order);
        return bean;
    }
}


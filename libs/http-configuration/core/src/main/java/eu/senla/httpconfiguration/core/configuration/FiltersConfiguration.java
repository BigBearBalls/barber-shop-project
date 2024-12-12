package eu.senla.httpconfiguration.core.configuration;

import eu.senla.httpconfiguration.core.factory.CustomYamlPropertySourceFactory;
import eu.senla.httpconfiguration.core.filter.FiltersExceptionHandler;
import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.*;
import org.springframework.core.Ordered;

import java.util.List;

@Configuration
@ConditionalOnProperty(prefix = FiltersConfiguration.PREFIX, name = "enable", havingValue = "true", matchIfMissing = true)
@PropertySource(value = "classpath:application-http-configuration-core.yaml", factory = CustomYamlPropertySourceFactory.class)
@ComponentScan(basePackages = "eu.senla.httpconfiguration.core.filter")
@Import(PropertiesConfiguration.class)
public class FiltersConfiguration {

    public final static String PREFIX = "http-configuration.core.filters";

    @Bean("filtersExceptionHandlerRegistrationBean")
    @ConditionalOnMissingBean(name = "filtersExceptionHandlerRegistrationBean")
    public FilterRegistrationBean<FiltersExceptionHandler> filtersExceptionHandlerRegistrationBean(
            @Autowired FiltersExceptionHandler handler) {
        return filterRegistrationBean(handler, List.of("/*"), Ordered.HIGHEST_PRECEDENCE);
    }

    protected <T extends Filter> FilterRegistrationBean<T> filterRegistrationBean(T filter, List<String> urls, int order) {
        FilterRegistrationBean<T> bean = new FilterRegistrationBean<>();
        bean.setFilter(filter);
        bean.setUrlPatterns(urls);
        bean.setOrder(order);
        return bean;
    }
}


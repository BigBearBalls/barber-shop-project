package eu.senla.httpconfiguration.exceptioncontroller.configuration;

import eu.senla.httpconfiguration.exceptioncontroller.filter.FiltersExceptionHandler;
import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.Ordered;

import java.util.List;

@AutoConfiguration
@ConditionalOnProperty(prefix = FiltersConfiguration.PREFIX, name = "enable", havingValue = "true", matchIfMissing = true)
@ComponentScan(basePackages = "eu.senla.httpconfiguration.exceptioncontroller.filter")
public class FiltersConfiguration {

    public final static String PREFIX = "http-configuration.exception-controller.filters";

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


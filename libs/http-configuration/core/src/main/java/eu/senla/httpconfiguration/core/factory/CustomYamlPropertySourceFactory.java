package eu.senla.httpconfiguration.core.factory;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.DefaultPropertySourceFactory;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class CustomYamlPropertySourceFactory extends DefaultPropertySourceFactory {
    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource encodedResource) throws IOException {
        Resource resource = encodedResource.getResource();
        System.out.println("DSAD: " + resource.getContentAsString(StandardCharsets.UTF_8));
        YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
        factory.setResources(resource);
        return new PropertiesPropertySource(
                Objects.requireNonNull(resource.getFilename()),
                Objects.requireNonNull(factory.getObject())
        );
    }
}

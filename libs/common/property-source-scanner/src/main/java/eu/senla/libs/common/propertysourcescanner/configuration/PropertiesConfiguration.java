package eu.senla.libs.common.propertysourcescanner.configuration;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Properties;

public class PropertiesConfiguration implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        try {
            loadYamlProperties(applicationContext.getEnvironment());
        } catch (IOException e) {
            throw new RuntimeException("Failed to load YAML properties", e);
        }
    }

    public void loadYamlProperties(ConfigurableEnvironment environment) throws IOException {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath*:application-*.yaml");
        for (Resource resource : resources) {
            System.out.println(resource.getFilename());
            YamlPropertiesFactoryBean yamlFactory = new YamlPropertiesFactoryBean();
            yamlFactory.setResources(resource);
            Properties properties = yamlFactory.getObject();
            System.out.println(resource.getContentAsString(StandardCharsets.UTF_8));
            if (properties != null) {
                environment.getPropertySources()
                        .addLast(new PropertiesPropertySource(Objects.requireNonNull(resource.getFilename()), properties));
            }
        }
    }
}

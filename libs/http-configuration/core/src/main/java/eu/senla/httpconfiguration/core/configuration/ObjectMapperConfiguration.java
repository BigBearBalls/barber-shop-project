package eu.senla.httpconfiguration.core.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import eu.senla.httpconfiguration.core.serialization.CustomLocalDateTimeDeserializer;
import eu.senla.httpconfiguration.core.serialization.CustomLocalDateTimeSerializer;
import eu.senla.httpconfiguration.core.serialization.TrimStringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class ObjectMapperConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        System.out.println("asdasdasd");
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(String.class, new TrimStringDeserializer());
        simpleModule.addDeserializer(LocalDateTime.class, new CustomLocalDateTimeDeserializer());
        simpleModule.addSerializer(LocalDateTime.class, new CustomLocalDateTimeSerializer());
        objectMapper.registerModule(simpleModule);
        return objectMapper;
    }
}

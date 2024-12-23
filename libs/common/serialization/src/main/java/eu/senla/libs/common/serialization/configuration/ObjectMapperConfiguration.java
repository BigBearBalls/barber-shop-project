package eu.senla.libs.common.serialization.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import eu.senla.common.enums.DepartmentRole;
import eu.senla.common.enums.PermissionValue;
import eu.senla.libs.common.serialization.deserializer.*;
import eu.senla.libs.common.serialization.serializer.CustomLocalDateSerializer;
import eu.senla.libs.common.serialization.serializer.CustomLocalDateTimeSerializer;
import eu.senla.libs.common.serialization.serializer.CustomLocalTimeSerializer;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@AutoConfiguration
public class ObjectMapperConfiguration {

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(String.class, new TrimStringDeserializer());
        simpleModule.addDeserializer(LocalDateTime.class, new CustomLocalDateTimeDeserializer());
        simpleModule.addDeserializer(LocalTime.class, new CustomLocalTimeDeserializer());
        simpleModule.addDeserializer(LocalDate.class, new CustomLocalDateDeserializer());
        simpleModule.addDeserializer(PermissionValue.class, new PermissionValueUpperCaseDeserializer());
        simpleModule.addDeserializer(DepartmentRole.class, new DepartmentRoleUpperCaseDeserializer());
        simpleModule.addSerializer(LocalDateTime.class, new CustomLocalDateTimeSerializer());
        simpleModule.addSerializer(LocalTime.class, new CustomLocalTimeSerializer());
        simpleModule.addSerializer(LocalDate.class, new CustomLocalDateSerializer());
        objectMapper.registerModule(simpleModule);
        return objectMapper;
    }
}

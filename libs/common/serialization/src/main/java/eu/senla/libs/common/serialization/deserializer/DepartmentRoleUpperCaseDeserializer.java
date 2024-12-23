package eu.senla.libs.common.serialization.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import eu.senla.common.enums.DepartmentRole;

import java.io.IOException;

public class DepartmentRoleUpperCaseDeserializer extends JsonDeserializer<DepartmentRole> {

    @Override
    public DepartmentRole deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        String value = p.getValueAsString();
        return DepartmentRole.valueOf(value.toUpperCase());
    }
}

package eu.senla.libs.common.serialization.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import eu.senla.common.enums.PermissionValue;

import java.io.IOException;

public class PermissionValueUpperCaseDeserializer extends JsonDeserializer<PermissionValue> {

    @Override
    public PermissionValue deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        String value = p.getValueAsString();
        return PermissionValue.valueOf(value.toUpperCase());
    }
}

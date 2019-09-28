package de.inveni.backend.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import de.inveni.backend.h2.Property;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PropertySerializer extends StdSerializer<List<Property>> {
    public PropertySerializer() {
        this(null);
    }

    public PropertySerializer(Class<List<Property>> t) {
        super(t);
    }

    @Override
    public void serialize(
            List<Property> students,
            JsonGenerator generator,
            SerializerProvider provider)
            throws IOException {
        for (Property s : students) {
            s.setUsers(null);
        }
        generator.writeObject(students);
    }
}

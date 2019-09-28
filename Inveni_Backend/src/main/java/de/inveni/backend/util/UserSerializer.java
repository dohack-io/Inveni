package de.inveni.backend.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import de.inveni.backend.h2.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserSerializer extends StdSerializer<List<User>> {
    public UserSerializer() {
        this(null);
    }

    public UserSerializer(Class<List<User>> t) {
        super(t);
    }

    @Override
    public void serialize(
            List<User> students,
            JsonGenerator generator,
            SerializerProvider provider)
            throws IOException {
        for (User s : students) {
            s.setProperties(null);
        }
        generator.writeObject(students);
    }
}

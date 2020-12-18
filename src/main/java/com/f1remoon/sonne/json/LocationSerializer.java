package com.f1remoon.sonne.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.bukkit.Location;

import java.io.IOException;

public class LocationSerializer extends StdSerializer<Location> {
    public LocationSerializer() {
        this(null);
    }

    public LocationSerializer(Class<Location> t) {
        super(t);
    }

    @Override
    public void serialize(Location value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("world", value.getWorld().getName());
        gen.writeNumberField("x", value.getX());
        gen.writeNumberField("y", value.getY());
        gen.writeNumberField("z", value.getZ());
        gen.writeEndObject();
    }
}

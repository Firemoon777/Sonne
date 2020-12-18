package com.f1remoon.sonne.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.io.IOException;

public class LocationDeserializer extends StdDeserializer<Location> {
    public LocationDeserializer() {
        this(null);
    }

    public LocationDeserializer(Class<Location> t) {
        super(t);
    }

    @Override
    public Location deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);
        String name = node.get("world").asText();
        Double x = node.get("x").asDouble();
        Double y = node.get("y").asDouble();
        Double z = node.get("z").asDouble();
        return new Location(Bukkit.getWorld(name), x, y, z);
    }
}

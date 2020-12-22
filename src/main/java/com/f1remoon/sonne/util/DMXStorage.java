package com.f1remoon.sonne.util;

import com.f1remoon.sonne.entity.*;
import com.f1remoon.sonne.json.LocationDeserializer;
import com.f1remoon.sonne.json.LocationSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import net.md_5.bungee.api.chat.hover.content.ItemSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DMXStorage implements Serializable {

    private static DMXStorage instance;

    public static synchronized DMXStorage getInstance() {
        if (instance == null) {
            instance = new DMXStorage();
        }
        return instance;
    }

    private DMXStorage() {
        this.objects = new ArrayList<>();
    }

    private List<DMXObject> objects;

    public List<DMXObject> getObjects() {
        return this.objects;
    }

    public void addMatrix(Location l1, Location l2) {
        LampMatrix matrix = new LampMatrix(l1, l2);
        objects.add(matrix);
    }

    public void addLamp(Location l) {
        Lamp lamp = new Lamp(l);
        objects.add(lamp);
    }

    public void addFireDispenser(Location l) {
        FireDispenser dispenser = new FireDispenser(l);
        objects.add(dispenser);
    }

    public void addBeacon(Location l) {
        Beacon beacon = new Beacon(l);
        objects.add(beacon);
    }

    public void addLight(Location l) {
        Light light = new Light(l);
        objects.add(light);
    }

    public void addTorch(Location l) {
        RedstoneTorch torch = new RedstoneTorch(l);
        objects.add(torch);
    }

    public static void load(File f) throws IOException, ClassNotFoundException {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Location.class, new LocationDeserializer());
        objectMapper.registerModule(module);

        instance = objectMapper.readValue(f, DMXStorage.class);
    }

    public static void save(File f) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Location.class, new LocationSerializer());
        objectMapper.registerModule(module);

        objectMapper.writeValue(f, instance);
    }

}

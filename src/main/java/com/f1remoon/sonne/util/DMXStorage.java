package com.f1remoon.sonne.util;

import com.f1remoon.sonne.entity.DMXObject;
import com.f1remoon.sonne.entity.LampMatrix;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DMXStorage implements Serializable {
    private static DMXStorage instance;
    private final List<DMXObject> objects;

    public static synchronized DMXStorage getInstance() {
        if (instance == null) {
            instance = new DMXStorage();
        }
        return instance;
    }

    private DMXStorage() {
        this.objects = new ArrayList<>();
    }

    public List<DMXObject> getObjects() {
        return this.objects;
    }

    public void addMatrix(Location l1, Location l2) {
        LampMatrix matrix = new LampMatrix(l1, l2);
        objects.add(matrix);
        Bukkit.getLogger().info("Added new object. Total size: " + objects.size());
    }

    public static void load(File f) throws IOException, ClassNotFoundException {
        ObjectMapper objectMapper = new ObjectMapper();
        instance = objectMapper.readValue(f, DMXStorage.class);
    }

    public static void save(File f) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(f, instance);
    }
}

package com.f1remoon.sonne.util;

import com.f1remoon.sonne.entity.DMXObject;
import com.f1remoon.sonne.entity.LampMatrix;
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
        Bukkit.getLogger().info("Added new object. Total size: " + objects.size());
    }

    public static void load(File f) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(f);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        instance = (DMXStorage) objectInputStream.readObject();
    }

    public static void save(File f) throws IOException {
        f.createNewFile();
        FileOutputStream outputStream = new FileOutputStream(f, false);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(instance);
    }

}

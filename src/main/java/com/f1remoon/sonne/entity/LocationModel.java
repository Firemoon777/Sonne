package com.f1remoon.sonne.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

@JsonAutoDetect(fieldVisibility = Visibility.NONE,
        setterVisibility = Visibility.NONE,
        getterVisibility = Visibility.NONE,
        isGetterVisibility = Visibility.NONE,
        creatorVisibility = Visibility.NONE)
public class LocationModel extends Location {
    @JsonProperty
    private final String modelName;
    @JsonProperty
    private final double modelX;
    @JsonProperty
    private final double modelY;
    @JsonProperty
    private final double modelZ;

    public LocationModel(String name, double x, double y, double z) {
        super(Bukkit.getWorld(name), x, y, z);
        this.modelX = x;
        this.modelY = y;
        this.modelZ = z;
        this.modelName = name;
    }

    public LocationModel(World world, double x, double y, double z) {
        super(world, x, y, z);
        this.modelX = x;
        this.modelY = y;
        this.modelZ = z;
        this.modelName = world.getName();
    }

    public LocationModel(World world, double x, double y, double z, float yaw, float pitch) {
        super(world, x, y, z, yaw, pitch);
        this.modelX = x;
        this.modelY = y;
        this.modelZ = z;
        this.modelName = world.getName();
    }

    public String getModelName() {
        return modelName;
    }

    public double getModelX() {
        return modelX;
    }

    public double getModelY() {
        return modelY;
    }

    public double getModelZ() {
        return modelZ;
    }
}

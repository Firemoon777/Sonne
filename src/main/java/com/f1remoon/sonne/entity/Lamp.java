package com.f1remoon.sonne.entity;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.data.BlockData;
import org.bukkit.scheduler.BukkitRunnable;

public class Lamp extends DMXObject {

    private static BlockData disabled = Bukkit.createBlockData("minecraft:redstone_lamp[lit=false]");
    private static BlockData enabled = Bukkit.createBlockData("minecraft:redstone_lamp[lit=true]");

    private Location location;

    public Lamp() {}

    public Lamp(Location l) {
        this.location = l;
        this.setChannelCount(1);
    }

    @Override
    public void apply(byte[] dmxData) {
        super.apply(dmxData);
        BlockData blockData;
        if(dmxData[0] == 0) {
            blockData = disabled;
        } else {
            blockData = enabled;
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                location.getBlock().setBlockData(blockData);
            }
        }.runTask(Bukkit.getPluginManager().getPlugin("Sonne"));
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}

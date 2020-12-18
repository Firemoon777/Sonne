package com.f1remoon.sonne.entity;

import com.f1remoon.sonne.util.Colors;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

public class Beacon extends DMXObject {

    private Location location;

    public Beacon() {}

    public Beacon(Location l) {
        this.location = new Location(l.getWorld(), l.getBlockX(), l.getBlockY() + 1, l.getBlockZ());
        this.setChannelCount(3);
    }

    @Override
    public void apply(byte[] dmxData) {
        super.apply(dmxData);
        String color = Colors.findColor(dmxData);
        new BukkitRunnable() {
            @Override
            public void run() {
                if(color != null) {
                    location.getBlock().setBlockData(Bukkit.createBlockData(String.format("minecraft:%s_stained_glass", color)));
                } else {
                    location.getBlock().setBlockData(Bukkit.createBlockData("minecraft:iron_block"));
                }
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

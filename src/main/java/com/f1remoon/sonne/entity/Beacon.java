package com.f1remoon.sonne.entity;

import com.f1remoon.sonne.util.Colors;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.data.BlockData;
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
        BlockData glass;
        if(color != null) {
            glass = Bukkit.createBlockData(String.format("minecraft:%s_stained_glass", color));
        } else {
            glass = Bukkit.createBlockData("minecraft:iron_block");
        }
        if(location.getBlock().getBlockData().matches(glass)) {
            return;
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                location.getBlock().setBlockData(glass);
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

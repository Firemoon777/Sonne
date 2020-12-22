package com.f1remoon.sonne.entity;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.data.BlockData;
import org.bukkit.scheduler.BukkitRunnable;

public class RedstoneTorch extends DMXObject {

    private static final BlockData disabled = Bukkit.createBlockData("minecraft:air");
    private static final BlockData enabled = Bukkit.createBlockData("minecraft:redstone_torch");

    private Location location;

    public RedstoneTorch() {}

    public RedstoneTorch(Location l) {
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
        if(location.getBlock().getBlockData().matches(blockData)) {
            return;
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

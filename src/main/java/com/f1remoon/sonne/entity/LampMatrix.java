package com.f1remoon.sonne.entity;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.data.BlockData;
import org.bukkit.scheduler.BukkitRunnable;

public class LampMatrix extends DMXObject {

    private Location start;
    private Location end;

    private static BlockData disabled = Bukkit.createBlockData("minecraft:redstone_lamp[lit=false]");
    private static BlockData enabled = Bukkit.createBlockData("minecraft:redstone_lamp[lit=true]");

    public LampMatrix(Location l1, Location l2) {
        this.start = new Location(l1.getWorld(),
                Math.min(l1.getBlockX(), l2.getBlockX()),
                Math.min(l1.getBlockY(), l2.getBlockY()),
                Math.min(l1.getBlockZ(), l2.getBlockZ())
        );
        this.end = new Location(l1.getWorld(),
                Math.max(l1.getBlockX(), l2.getBlockX()),
                Math.max(l1.getBlockY(), l2.getBlockY()),
                Math.max(l1.getBlockZ(), l2.getBlockZ())
        );
        Integer lampCount = (end.getBlockX() - start.getBlockX() + 1) *
                (end.getBlockY() - start.getBlockY() + 1) *
                (end.getBlockZ() - start.getBlockZ() + 1);
        this.setChannelCount(lampCount*3);
    }

    @Override
    public void apply(byte[] dmxData) {
        super.apply(dmxData);

        Integer i = 0;
        for(int x = start.getBlockX(); x <= end.getBlockX(); x++) {
            for(int z = start.getBlockZ(); z < end.getBlockZ(); z++) {
                for(int y = start.getBlockY(); y < end.getBlockY(); y++) {
                    BlockData blockData;
                    byte r = dmxData[i + 0];
                    byte g = dmxData[i + 1];
                    byte b = dmxData[i + 2];
                    i += 3;

                    if(r >= 0 && g >= 0 && b >= 0) {
                        blockData = disabled;
                    } else {
                        blockData = enabled;
                    }

                    Location l = new Location(start.getWorld(), x, y, z);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            l.getBlock().setBlockData(blockData);
                        }
                    }.runTask(Bukkit.getPluginManager().getPlugin("Sonne"));
                }
            }
        }

    }

    public Location getStart() {
        return start;
    }

    public void setStart(Location start) {
        this.start = start;
    }

    public Location getEnd() {
        return end;
    }

    public void setEnd(Location end) {
        this.end = end;
    }
}

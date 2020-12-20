package com.f1remoon.sonne.entity;

import com.f1remoon.sonne.util.Colors;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;
import ru.beykerykt.lightapi.LightAPI;
import ru.beykerykt.lightapi.LightType;
import ru.beykerykt.lightapi.chunks.ChunkInfo;

public class Light extends DMXObject {

    private Location location;
    private Integer currentLevel;

    public Light() {}

    public Light(Location l) {
        this.location = new Location(l.getWorld(), l.getBlockX(), l.getBlockY() + 1, l.getBlockZ());
        this.setChannelCount(3);
    }

    @Override
    public void apply(byte[] dmxData) {
        super.apply(dmxData);
        Integer level = ((int)dmxData[0] & 0xFF) / 16;
        if(level.equals(currentLevel)) {
            return;
        }
        currentLevel = level;
        new BukkitRunnable() {
            @Override
            public void run() {
                LightAPI.deleteLight(location, LightType.BLOCK, true);
                if(level > 0) {
                    LightAPI.createLight(location, LightType.BLOCK, level, true);
                }
                for(ChunkInfo info : LightAPI.collectChunks(location, LightType.BLOCK, level)) {
                    LightAPI.updateChunk(info, LightType.BLOCK);
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

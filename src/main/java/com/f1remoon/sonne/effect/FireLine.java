package com.f1remoon.sonne.effect;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;

public class FireLine {

    private static void createFireLevel(Location l, int duration, boolean simple) {
        new BukkitRunnable() {
            int time = 0;
            @Override
            public void run() {
                if(time >= duration) {
                    this.cancel();
                    return;
                }
                if(simple) {
                    l.getWorld().getBlockAt(l).setType(Material.FIRE);
                } else {
                    l.getWorld().getBlockAt(l).setType(Material.ACACIA_FENCE);
                    for (int xOffset = -1; xOffset <= 1; xOffset++) {
                        for (int zOffset = -1; zOffset <= 1; zOffset++) {
                            Location fire = new Location(l.getWorld(), l.getX() + xOffset, l.getY(), l.getZ() + zOffset);
                            if (xOffset == 0 && zOffset == 0) {
                                continue;
                            }
                            l.getWorld().getBlockAt(fire).setType(Material.FIRE);
                        }
                    }
                }
                time += 2;
            }
        }.runTaskTimer(Bukkit.getPluginManager().getPlugin("Sonne"), 0, 2);

        new BukkitRunnable() {
            @Override
            public void run() {
                l.getWorld().getBlockAt(l).setType(Material.AIR);
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Sonne"), duration);
    }

    public static void burn(Location start, int height, int duration) {
        new BukkitRunnable() {
            int levels = 0;
            @Override
            public void run() {
                Location l = start.clone();
                l.setY(l.getY() + levels);
                createFireLevel(l, duration, levels == 0);
                if(++levels >= height) this.cancel();
            }
        }.runTaskTimer(Bukkit.getPluginManager().getPlugin("Sonne"), 0, 5);
    }
}

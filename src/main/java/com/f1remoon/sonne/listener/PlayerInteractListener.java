package com.f1remoon.sonne.listener;

import com.f1remoon.sonne.util.DMXStorage;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerInteractListener implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if(event.getHand() != EquipmentSlot.HAND) {
            return;
        }
        if(event.getItem() == null) {
            return;
        }
        if(event.getItem().getType() == Material.GLOWSTONE_DUST) {
            Block blk = event.getClickedBlock();
            if(blk == null) {
                return;
            }
            Location l = blk.getLocation();

            if(blk.getType() == Material.REDSTONE_LAMP) {
                DMXStorage.getInstance().addLamp(l);
            } else if(blk.getType() == Material.DISPENSER) {
                DMXStorage.getInstance().addFireDispenser(l);
            } else if(blk.getType() == Material.BEACON) {
                DMXStorage.getInstance().addBeacon(l);
            } else if(blk.getType() == Material.GLOWSTONE) {
                DMXStorage.getInstance().addLight(l);
                l.getBlock().setType(Material.AIR);
            } else {
                return;
            }
            event.setCancelled(true);
            event.getPlayer().sendMessage(String.format("Success, coords: %d %d %d", l.getBlockX(), l.getBlockY(), l.getBlockZ()));
        }
    }
}

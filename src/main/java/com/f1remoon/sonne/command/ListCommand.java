package com.f1remoon.sonne.command;

import com.f1remoon.sonne.entity.*;
import com.f1remoon.sonne.util.DMXStorage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.Collections;
import java.util.List;

public class ListCommand implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("Total objects: " + DMXStorage.getInstance().getObjects().size());
        Integer startChannel = 0;
        for(DMXObject o : DMXStorage.getInstance().getObjects()) {

            String type = "";
            String data = "";
            if(o instanceof LampMatrix) {
                type = "LampMatrix";
            } else if(o instanceof Beacon) {
                Beacon beacon = (Beacon)o;
                type = "Beacon";
                data = String.format("(%d; %d; %d)", beacon.getLocation().getBlockX(), beacon.getLocation().getBlockY(), beacon.getLocation().getBlockZ());
            } else if(o instanceof Lamp) {
                Lamp lamp = (Lamp)o;
                type = "Lamp";
                data = String.format("(%d; %d; %d)", lamp.getLocation().getBlockX(), lamp.getLocation().getBlockY(), lamp.getLocation().getBlockZ());
            } else if(o instanceof Light) {
                Light light = (Light)o;
                type = "Light";
                data = String.format("(%d; %d; %d)", light.getLocation().getBlockX(), light.getLocation().getBlockY(), light.getLocation().getBlockZ());
            } else if(o instanceof FireDispenser) {
                FireDispenser fireDispenser = (FireDispenser)o;
                type = "FireDispenser";
                data = String.format("(%d; %d; %d)", fireDispenser.getLocation().getBlockX(), fireDispenser.getLocation().getBlockY(), fireDispenser.getLocation().getBlockZ());
            }
            sender.sendMessage(String.format("[%4d-%4d] %s %s", startChannel, startChannel+o.getChannelCount(), type, data));

            startChannel += o.getChannelCount();
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return Collections.emptyList();
    }
}

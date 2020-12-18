package com.f1remoon.sonne.command;

import com.f1remoon.sonne.entity.DMXObject;
import com.f1remoon.sonne.entity.LampMatrix;
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
            sender.sendMessage("[" + o.getName() + "]");
            sender.sendMessage("  DMX Universe: " + o.getUniverse());
            sender.sendMessage("  Start channel: " + startChannel);
            sender.sendMessage("  Total channels: " + o.getChannelCount());
            startChannel += o.getChannelCount();

            if(o instanceof LampMatrix) {
                LampMatrix lampMatrix = (LampMatrix)o;
                sender.sendMessage("  Type: LampMatrix");
                sender.sendMessage(String.format(
                        "  Location: (%d, %d, %d) -> (%d, %d, %d)",
                        lampMatrix.getStart().getBlockX(),
                        lampMatrix.getStart().getBlockY(),
                        lampMatrix.getStart().getBlockZ(),
                        lampMatrix.getEnd().getBlockX(),
                        lampMatrix.getEnd().getBlockY(),
                        lampMatrix.getEnd().getBlockZ()
                ));
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return Collections.emptyList();
    }
}

package com.f1remoon.sonne.command;

import com.f1remoon.sonne.entity.LampMatrix;
import com.f1remoon.sonne.util.DMXStorage;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AddCommand implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("Only for players!");
            return true;
        }
        Player player = (Player)sender;
        if(args[0].equals("matrix")) {
            if(args.length != 7) {
                sender.sendMessage("Incorrect argc for matrix type");
                return true;
            }
            Location l1, l2;
            try {
                l1 = new Location(
                        player.getLocation().getWorld(),
                        Integer.parseInt(args[1]),
                        Integer.parseInt(args[2]),
                        Integer.parseInt(args[3])
                );
                l2 = new Location(
                        player.getLocation().getWorld(),
                        Integer.parseInt(args[4]),
                        Integer.parseInt(args[5]),
                        Integer.parseInt(args[6])
                );
            } catch (NumberFormatException e) {
                sender.sendMessage("Incorrect coords");
                return true;
            }
            DMXStorage.getInstance().addMatrix(l1, l2);
        }
        sender.sendMessage("Done!");
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(args.length == 1) {
            return Arrays.asList(
                    "matrix"
            );
        }
        if(sender instanceof Player) {
            Player player = (Player)sender;
            Integer x = player.getLocation().getBlockX();
            Integer y = player.getLocation().getBlockY();
            Integer z = player.getLocation().getBlockZ();

            if((args.length - 2) % 3 == 0) {
                return Arrays.asList(
                        String.format("%d", x),
                        String.format("%d %d", x, y),
                        String.format("%d %d %d", x, y, z)
                );
            }
            if((args.length - 2) % 3 == 1) {
                return Arrays.asList(
                        String.format("%d", y),
                        String.format("%d %d", y, z)
                );
            }
            if((args.length - 2) % 3 == 2) {
                return Arrays.asList(
                        String.format("%d", z)
                );
            }
        }
        return Collections.emptyList();
    }
}

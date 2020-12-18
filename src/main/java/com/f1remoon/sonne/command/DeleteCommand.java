package com.f1remoon.sonne.command;

import com.f1remoon.sonne.entity.DMXObject;
import com.f1remoon.sonne.entity.LampMatrix;
import com.f1remoon.sonne.util.DMXStorage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.Collections;
import java.util.List;

public class DeleteCommand implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        DMXStorage.getInstance().getObjects().clear();
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return Collections.emptyList();
    }
}

package com.f1remoon.sonne;

import com.f1remoon.sonne.command.AddCommand;
import com.f1remoon.sonne.command.DeleteCommand;
import com.f1remoon.sonne.command.ListCommand;
import com.f1remoon.sonne.listener.PlayerInteractListener;
import com.f1remoon.sonne.server.E131Server;
import com.f1remoon.sonne.util.DMXStorage;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketException;

public final class Sonne extends JavaPlugin {

    private E131Server server;
    private File dmxFile;

    @Override
    public void onEnable() {
        File data = getDataFolder();
        if(!data.exists()) {
            data.mkdir();
        }

        dmxFile = new File(data.toString() + "/dmx.json");
        try {
            DMXStorage.load(dmxFile);
        } catch (FileNotFoundException ignored) {
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            this.server = new E131Server();
            this.server.start();
        } catch (SocketException e) {
            e.printStackTrace();
        }

        getCommand("add").setExecutor(new AddCommand());
        getCommand("list").setExecutor(new ListCommand());
        getCommand("delete").setExecutor(new DeleteCommand());
        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        this.server.interrupt();

        try {
            DMXStorage.save(dmxFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

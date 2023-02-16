package de.theredend2000.maptesting;

import de.theredend2000.maptesting.arenas.Map;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public final class Main extends JavaPlugin {

    public YamlConfiguration yaml;
    public File data = new File(getDataFolder(), "database.yml");

    private HashMap<Map, Player> mapPlayers;
    private commands commands;


    @Override
    public void onEnable() {
        saveDefaultConfig();
        this.yaml = YamlConfiguration.loadConfiguration(this.data);
        this.saveData();

        mapPlayers = new HashMap<>();
        commands = new commands(this);
        getCommand("map").setExecutor(new commands(this));

    }

    public void saveData() {
        try {
            this.yaml.save(this.data);
        } catch (IOException var2) {
            var2.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public HashMap<Map, Player> getMapPlayers() {
        return mapPlayers;
    }

    public de.theredend2000.maptesting.commands getCommands() {
        return commands;
    }
}

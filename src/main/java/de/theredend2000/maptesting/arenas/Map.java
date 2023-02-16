package de.theredend2000.maptesting.arenas;

import de.theredend2000.maptesting.Main;
import org.bukkit.Location;

public class Map {


    private Main plugin;
    private String name;
    private String builder;
    private String players;
    private Location[] spawnLocations = new Location[10];
    private Location spectatorLocation;
    private Location seekerStartLocation, seekerWaitLocation;

    private int votes;

    public Map(Main plugin, String name) {
        this.plugin = plugin;
        this.name = name;

        if(exists())
            builder = plugin.yaml.getString("Arenas."+name+".Builder");
    }

    public void create(String builder) {
        this.builder = builder;
        plugin.yaml.set("Arenas."+ name+".Builder", builder);
        plugin.saveData();
    }

    public void load() {
        for(int i = 0; i < spawnLocations.length; i++)
            spawnLocations[i] = new ConfigLocationUtil(plugin, "Arenas."+name+"."+ (i + 1)).loadLocation();
        spectatorLocation = new ConfigLocationUtil(plugin, "Arenas."+name+".Spectator").loadLocation();
        seekerWaitLocation = new ConfigLocationUtil(plugin, "Arenas."+name+".SeekerWait").loadLocation();
        seekerStartLocation = new ConfigLocationUtil(plugin, "Arenas."+name+".SeekerStart").loadLocation();
    }

    public boolean exists() {
        return (plugin.yaml.getString("Arenas." + name + ".Builder") != null);
    }

    public boolean playable() {
        if(!plugin.yaml.contains("Arenas." + name + ".Builder")) return false;
        if(!plugin.yaml.contains("Arenas."+name+".SeekerStart")) return false;
        if(!plugin.yaml.contains("Arenas."+name+".SeekerWait")) return false;
        if(!plugin.yaml.contains("Arenas."+name+".Spectator")) return false;
        for(int i = 1; i < 10+ 1; i++) {
            if(!plugin.yaml.contains("Arenas." + name + "."+i)) return false;
        }
        if(!plugin.yaml.getBoolean("Arenas."+name+".isFinished")) return false;
        return true;
    }

    public String getName() {
        return name;
    }

    public String getBuilder() {
        return builder;
    }

    public Location[] getSpawnLocations() {
        return spawnLocations;
    }

    public Location getSpectatorLocation() {
        return spectatorLocation;
    }

    public Location getSeekerWaitLocation() {
        return seekerWaitLocation;
    }

    public Location getSeekerStartLocation() {
        return seekerStartLocation;
    }

}

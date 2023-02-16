package de.theredend2000.maptesting.arenas;

import de.theredend2000.maptesting.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigLocationUtil {

    private Main plugin;
    private Location location;
    private String root;

    public ConfigLocationUtil(Main plugin,Location location, String root){
        this.plugin = plugin;
        this.location = location;
        this.root = root;
    }

    public void saveBlockLocation() {
        FileConfiguration config = plugin.yaml;
        config.set(root + ".World", location.getWorld().getName());
        config.set(root + ".X", location.getBlockX());
        config.set(root + ".Y", location.getBlockY());
        config.set(root + ".Z", location.getBlockZ());
        plugin.saveData();
    }

    public Block loadBlockLocation() {
        FileConfiguration config = plugin.yaml;
        if(config.contains(root)) {
            World world = Bukkit.getWorld(config.getString(root+".World"));
            int x = plugin.yaml.getInt(root +".X"),
                    y = plugin.yaml.getInt(root +".Y"),
                    z = plugin.yaml.getInt(root +".Z");
            return new Location(world, x,y,z).getBlock();
        }
        return null;
    }

    public ConfigLocationUtil(Main plugin, String root) {
        this(plugin, null, root);
    }

    public void saveLocation() {
        FileConfiguration config = plugin.yaml;
        config.set(root+".World", location.getWorld().getName());
        config.set(root+".X", location.getX());
        config.set(root+".Y", location.getY());
        config.set(root+".Z", location.getZ());
        config.set(root+".Yaw", location.getYaw());
        config.set(root+".Pitch", location.getPitch());
        plugin.saveData();
    }
    public Location loadLocation() {
        FileConfiguration config = plugin.yaml;
        if(config.contains(root)) {
            World world = Bukkit.getWorld(config.getString(root + ".World"));
            double x = config.getDouble(root+".X"),
                    y = config.getDouble(root+".Y"),
                    z = config.getDouble(root+".Z");
            float yaw = (float) config.getDouble(root+".Yaw"),
                    pitch = (float) config.getDouble(root+".Pitch");
            return new Location(world,x,y,z,yaw,pitch);
        }else
            return  null;

    }

}

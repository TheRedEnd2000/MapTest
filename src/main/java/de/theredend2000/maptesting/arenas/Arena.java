package de.theredend2000.maptesting.arenas;

import de.theredend2000.maptesting.Main;
import org.bukkit.entity.Player;

public class Arena {

    private Map map;
    private Main plugin;
    private Player player;

    public Arena(Main plugin, Map map){
        this.plugin = plugin;
        this.map = map;
        this.player = plugin.getMapPlayers().get(map);
    }

    public Map getMap() {
        return map;
    }

    public Player getPlayers() {
        return player;
    }
}

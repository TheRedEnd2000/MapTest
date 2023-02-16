package de.theredend2000.maptesting.gamestates;

import de.theredend2000.maptesting.Main;
import de.theredend2000.maptesting.arenas.Map;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class LobbyState extends GameState{

    private Main plugin;

    public LobbyState(Main plugin){
        this.plugin = plugin;
    }

    @Override
    public void start() {
        Map map = plugin.getCommands().getArena().getMap();
        Bukkit.broadcastMessage(map.getName());

    }

    @Override
    public void stop() {

    }
}

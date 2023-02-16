package de.theredend2000.maptesting;

import de.theredend2000.maptesting.arenas.Arena;
import de.theredend2000.maptesting.arenas.Map;
import de.theredend2000.maptesting.gamestates.GameState;
import de.theredend2000.maptesting.gamestates.GameStateManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commands implements CommandExecutor {

    private Main plugin;
    private Map map;
    private Arena arena;
    private GameStateManager gameStateManager;

    public commands(Main plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if(args[0].equalsIgnoreCase("join")){
            if(args.length == 2){
                map = new Map(plugin, args[1]);
                if(map.exists()) {
                    if (!plugin.getMapPlayers().containsValue(player)) {
                        player.sendMessage("joined");
                        plugin.getMapPlayers().put(map,player);
                        arena = new Arena(plugin,map);
                        gameStateManager = new GameStateManager(plugin);
                        gameStateManager.setGameStates(map, GameState.LOBBY_STATE);
                    }
                }else
                    player.sendMessage("Die gibts net");
            }
        }else if(args[0].equalsIgnoreCase("leave")){
            if(args.length == 2){
                map = new Map(plugin, args[1]);
                if(map.exists()) {
                    if (plugin.getMapPlayers().containsValue(player)) {
                        player.sendMessage("left");
                        plugin.getMapPlayers().remove(map);
                        arena = new Arena(plugin, map);
                        gameStateManager = new GameStateManager(plugin);
                        if (plugin.getMapPlayers().values().size() == 0) {
                            gameStateManager.stopGame(map);
                        }
                    }
                }else
                    player.sendMessage("Die gibts schon");
            }
        }else if(args[0].equalsIgnoreCase("create")){
            if(args.length == 2){
                Map map = new Map(plugin, args[1]);
                map.create(player.getName());
                player.sendMessage("Map "+ map.getName()+" created");
            }
        }
        return false;
    }

    public Arena getArena() {
        return arena;
    }
}

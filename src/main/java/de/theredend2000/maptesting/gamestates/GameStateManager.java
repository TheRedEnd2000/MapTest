package de.theredend2000.maptesting.gamestates;

import de.theredend2000.maptesting.Main;
import de.theredend2000.maptesting.arenas.Map;

import java.util.HashMap;

public class GameStateManager {

    private Main plugin;
    private GameState[] gameStates;
    private GameState currentGameState;
    private HashMap<Map, GameState> mapGameState;

    public GameStateManager(Main plugin) {
        this.plugin = plugin;
        gameStates = new GameState[3];

        gameStates[GameState.LOBBY_STATE] = new LobbyState(plugin);
        gameStates[GameState.INGAME_STATE] = new IngameState(plugin);
        gameStates[GameState.ENDING_STATE] = new EndingState(plugin);
    }

    public void setGameStates(Map map, int gameStateID) {
        if(currentGameState != null)
            currentGameState.stop();
        currentGameState = gameStates[gameStateID];
        currentGameState.start();
        mapGameState.put(map,currentGameState);
    }

    /*public void stopCurrentGameState() {
        if(currentGameState != null) {
            currentGameState.stop();
            currentGameState = null;
        }
    }*/

    public void stopGame(Map map){
        mapGameState.remove(map);
    }

    public HashMap<Map, GameState> getMapGameState() {
        return mapGameState;
    }

    public GameState getCurrentGameState(Map map) {
        return mapGameState.get(map);
    }

    public Main getPlugin() {
        return plugin;
    }
}

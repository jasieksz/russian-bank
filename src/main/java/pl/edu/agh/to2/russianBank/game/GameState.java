package pl.edu.agh.to2.russianBank.game;

import java.util.List;

public class GameState {

    private List<Player> players;
    private GameTable gameTable;

    public GameState(List<Player> players, GameTable gameTable) {
        this.players = players;
        this.gameTable = gameTable;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public GameTable getGameTable() {
        return gameTable;
    }
}

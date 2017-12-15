package main.java.pl.edu.agh.to2.russianBank.game;

import java.util.List;

public class Game {
    private List<Player> players;
    private GameTable gameTable;

    public Game(List<Player> players, GameTable gameTable) {
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

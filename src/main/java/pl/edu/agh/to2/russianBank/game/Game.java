package main.java.pl.edu.agh.to2.russianBank.game;

import java.util.List;

public class Game {
    private List<main.java.pl.edu.agh.to2.russianBank.game.Player> players;
    private main.java.pl.edu.agh.to2.russianBank.game.GameTable gameTable;

    public Game(List<main.java.pl.edu.agh.to2.russianBank.game.Player> players, main.java.pl.edu.agh.to2.russianBank.game.GameTable gameTable) {
        this.players = players;
        this.gameTable = gameTable;
    }

    public List<main.java.pl.edu.agh.to2.russianBank.game.Player> getPlayers() {
        return players;
    }

    public main.java.pl.edu.agh.to2.russianBank.game.GameTable getGameTable() {
        return gameTable;
    }
}

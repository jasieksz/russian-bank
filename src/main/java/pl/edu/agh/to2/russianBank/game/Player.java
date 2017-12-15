package main.java.pl.edu.agh.to2.russianBank.game;

public class Player {

    private String name;
    private main.java.pl.edu.agh.to2.russianBank.game.PlayerDeck playerDeck;

    public Player(String name, main.java.pl.edu.agh.to2.russianBank.game.PlayerDeck playerDeck) {
        this.name = name;
        this.playerDeck = playerDeck;
    }

    public String getName() {
        return name;
    }

    public main.java.pl.edu.agh.to2.russianBank.game.PlayerDeck getPlayerDeck() {
        return playerDeck;
    }
}

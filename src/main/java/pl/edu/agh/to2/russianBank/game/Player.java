package pl.edu.agh.to2.russianBank.game;

public class Player {

    private String name;
    private PlayerDeck playerDeck;

    public Player(String name, PlayerDeck playerDeck) {
        this.name = name;
        this.playerDeck = playerDeck;
    }

    public String getName() {
        return name;
    }

    public PlayerDeck getPlayerDeck() {
        return playerDeck;
    }
}

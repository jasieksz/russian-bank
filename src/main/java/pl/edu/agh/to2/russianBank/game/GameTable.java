package main.java.pl.edu.agh.to2.russianBank.game;

import java.util.List;

public class GameTable {
    private List<main.java.pl.edu.agh.to2.russianBank.game.PlayerDeck> playersCard;
    private List<main.java.pl.edu.agh.to2.russianBank.game.House> houses;
    private List<main.java.pl.edu.agh.to2.russianBank.game.Foundation> foundations;

    public GameTable(List<main.java.pl.edu.agh.to2.russianBank.game.PlayerDeck> playersCard, List<main.java.pl.edu.agh.to2.russianBank.game.House> houses, List<main.java.pl.edu.agh.to2.russianBank.game.Foundation> foundations) {
        this.playersCard = playersCard;
        this.houses = houses;
        this.foundations = foundations;
    }

    public List<main.java.pl.edu.agh.to2.russianBank.game.PlayerDeck> getPlayers() {
        return playersCard;
    }

    public List<main.java.pl.edu.agh.to2.russianBank.game.House> getHouses() {
        return houses;
    }

    public List<main.java.pl.edu.agh.to2.russianBank.game.Foundation> getFoundations() {
        return foundations;
    }
}

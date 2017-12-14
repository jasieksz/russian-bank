package main.java.pl.edu.agh.to2.russianBank.game;

import java.util.List;

public class GameTable {
    private List<PlayerDeck> playersCard;
    private List<House> houses;
    private List<Foundation> foundations;

    public GameTable(List<PlayerDeck> playersCard, List<House> houses, List<Foundation> foundations) {
        this.playersCard = playersCard;
        this.houses = houses;
        this.foundations = foundations;
    }

    public List<PlayerDeck> getPlayers() {
        return playersCard;
    }

    public List<House> getHouses() {
        return houses;
    }

    public List<Foundation> getFoundations() {
        return foundations;
    }
}

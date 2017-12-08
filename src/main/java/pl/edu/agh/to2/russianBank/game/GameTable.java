package main.java.pl.edu.agh.to2.russianBank.game;

import java.util.List;

public class GameTable {
    private List<Player> players;
    private List<House> houses;
    private List<Foundation> foundations;

    public GameTable(List<Player> players, List<House> houses, List<Foundation> foundations) {
        this.players = players;
        this.houses = houses;
        this.foundations = foundations;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<House> getHouses() {
        return houses;
    }

    public List<Foundation> getFoundations() {
        return foundations;
    }
}

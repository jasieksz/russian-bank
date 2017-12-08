package main.java.pl.edu.agh.to2.russianBank.game;

public class Player {
    private String name;
    private Hand hand; // TODO : is private correct?
    private Waste waste;

    public Player(String name, Hand hand, Waste waste) {
        this.name = name;
        this.hand = hand;
        this.waste = waste;
    }

    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }

    public Waste getWaste() {
        return waste;
    }
}

package pl.edu.agh.to2.russianBank.game;

public class PlayerDeck {
    private Hand hand;
    private Waste waste;

    // TODO : observe hand -> when empty : swap waste with hand if waste also empty => YOU WIN!
    // TODO : simple observable is not enough, we must notify opponent about swap. Can we make it special case move - executed when we try to takeTopCard from empty Hand? @J
    public PlayerDeck(Hand hand, Waste waste) {
        this.hand = hand;
        this.waste = waste;
    }

    public Hand getHand() {
        return hand;
    }

    public Waste getWaste() {
        return waste;
    }
}

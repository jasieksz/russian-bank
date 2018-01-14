package pl.edu.agh.to2.russianBank.game;

public class PlayerDeck {
    private Hand hand;
    private Waste waste;

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

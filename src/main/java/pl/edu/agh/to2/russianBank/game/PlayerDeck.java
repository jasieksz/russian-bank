package main.java.pl.edu.agh.to2.russianBank.game;

public class PlayerDeck {
    // TODO : is private correct? pretty sure it is
    private main.java.pl.edu.agh.to2.russianBank.game.Hand hand;
    private main.java.pl.edu.agh.to2.russianBank.game.Waste waste;

    public PlayerDeck(main.java.pl.edu.agh.to2.russianBank.game.Hand hand, main.java.pl.edu.agh.to2.russianBank.game.Waste waste) {
        this.hand = hand;
        this.waste = waste;
    }

    public main.java.pl.edu.agh.to2.russianBank.game.Hand getHand() {
        return hand;
    }

    public main.java.pl.edu.agh.to2.russianBank.game.Waste getWaste() {
        return waste;
    }
}

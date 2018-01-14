package pl.edu.agh.to2.russianBank.game;

import com.google.common.base.MoreObjects;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerDeck that = (PlayerDeck) o;
        return Objects.equals(hand, that.hand) &&
                Objects.equals(waste, that.waste);
    }

    @Override
    public int hashCode() {

        return Objects.hash(hand, waste);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("hand", hand)
                .add("waste", waste)
                .toString();
    }
}

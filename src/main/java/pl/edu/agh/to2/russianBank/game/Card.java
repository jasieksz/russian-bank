package pl.edu.agh.to2.russianBank.game;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.Objects;

public class Card {
    private CardSuit suit;
    private CardRank rank;

    public Card(CardSuit suit, CardRank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public List<CardSuit> getOppositeSuits() {
        return ImmutableList.of(
                CardSuit.values()[(this.suit.getSuitId() + 1) % CardSuit.values().length],
                CardSuit.values()[(this.suit.getSuitId() + 3) % CardSuit.values().length]
        );
    }

    public CardSuit getSuit() {
        return suit;
    }

    public CardRank getRank() {
        return rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return suit == card.suit &&
                rank == card.rank;
    }

    @Override
    public int hashCode() {

        return Objects.hash(suit, rank);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("suit", suit)
                .add("rank", rank)
                .toString();
    }
}

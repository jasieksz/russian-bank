package pl.edu.agh.to2.russianBank.game;

import com.google.common.collect.ImmutableList;

import java.util.List;

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
}

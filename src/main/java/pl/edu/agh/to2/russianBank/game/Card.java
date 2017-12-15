package main.java.pl.edu.agh.to2.russianBank.game;

import java.util.Collections;
import java.util.List;

public class Card {
    private CardSuit suit;
    private CardRank rank;

    public Card(CardSuit suit, CardRank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public List<CardSuit> getOppositeSuits() {
        List ret = Collections.emptyList();
        ret.add(CardSuit.values()[(this.suit.ordinal() + 1) % 4]);
        ret.add(CardSuit.values()[(this.suit.ordinal() + 3) % 4]);
        return ret;
    }

    public CardSuit getSuit() {
        return suit;
    }

    public CardRank getRank() {
        return rank;
    }
}

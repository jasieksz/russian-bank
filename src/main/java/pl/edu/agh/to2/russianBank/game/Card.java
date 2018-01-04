package pl.edu.agh.to2.russianBank.game;

import java.util.ArrayList;
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
        List ret = new ArrayList<CardSuit>();
        ret.add(CardSuit.values()[(this.suit.getSuitId() + 1) % CardSuit.values().length]);
        ret.add(CardSuit.values()[(this.suit.getSuitId() + 3) % CardSuit.values().length]);
        return ret;
    }

    public CardSuit getSuit() {
        return suit;
    }

    public CardRank getRank() {
        return rank;
    }
}

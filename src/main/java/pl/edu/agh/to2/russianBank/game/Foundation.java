package main.java.pl.edu.agh.to2.russianBank.game;

import java.util.List;

public class Foundation extends ICardSet { // ACE -> 2 -> 3 -> ... -> KING

    private List<Card> cards;
    private Integer position;
    private CardSuit suit;

    public Foundation(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    Boolean tryTakeTopCard() { // TODO : Wrong MOVE!
        return null;
    }

    @Override
    Card takeTopCard() {  // TODO : Wrong MOVE!
        return null;
    }

    @Override
    void putCard() {

    }

    @Override
    Integer getSize() {
        return null;
    }

    @Override
    Boolean isVisible() {
        return true;
    }
}

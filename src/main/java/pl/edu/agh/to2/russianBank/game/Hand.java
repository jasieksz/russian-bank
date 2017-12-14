package main.java.pl.edu.agh.to2.russianBank.game;

import java.util.List;
import java.util.Optional;

public class Hand extends ICardSet {

    private List<Card> cards;

    public Hand(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public Optional<Card> takeTopCard() { // removes top card
        return Optional.empty();
    }

    @Override
    public Boolean putCard(Card card) {
        return this.cards.add(card);
    }


    @Override
    public Integer getSize() {
        return null;
    }

    @Override
    public Boolean isVisible() {
        return false;
    }
}

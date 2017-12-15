package main.java.pl.edu.agh.to2.russianBank.game;

import java.util.List;
import java.util.Optional;

public class Hand extends ICardSet {

    private List<Card> cards;

    public Hand(List<Card> cards) {
        this.cards = cards;
    }


    // TODO should we automatically reshuffle if no more cards present?
    // TODO : PlayerDeck should be responsible for this, it contains both hand & waste @J
    @Override
    public Optional<Card> takeTopCard() { // removes top card
        return cards.size()>0 ? Optional.of(cards.remove(cards.size()-1)) : Optional.empty();
    }

    @Override
    public Boolean putCard(Card card) {
        return this.cards.add(card);
    }


    @Override
    public Integer getSize() {
        return cards.size();
    }

    @Override
    public Boolean isVisible() {
        return false;
    }
}

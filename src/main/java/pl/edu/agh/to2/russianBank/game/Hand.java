package main.java.pl.edu.agh.to2.russianBank.game;

import java.util.List;
import java.util.Optional;

public class Hand extends main.java.pl.edu.agh.to2.russianBank.game.ICardSet {

    private List<main.java.pl.edu.agh.to2.russianBank.game.Card> cards;

    public Hand(List<main.java.pl.edu.agh.to2.russianBank.game.Card> cards) {
        this.cards = cards;
    }


    //TODO should we automatically reshuffle if no more cards present?
    @Override
    public Optional<main.java.pl.edu.agh.to2.russianBank.game.Card> takeTopCard() { // removes top card
        return cards.size()>0 ? Optional.of(cards.remove(cards.size()-1)) : Optional.empty();
    }

    @Override
    public Boolean putCard(main.java.pl.edu.agh.to2.russianBank.game.Card card) {
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

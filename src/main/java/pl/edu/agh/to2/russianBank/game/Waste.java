package main.java.pl.edu.agh.to2.russianBank.game;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Waste extends ICardSet {

    public Waste() {
        cards = Collections.emptyList();
    }

    private List<Card> cards;

    @Override
    public Optional<Card> takeTopCard() {
        return null;
    }

    @Override
    public Boolean putCard(Card card) {

    }

    @Override
    public Integer getSize() {
        return null;
    }

    @Override
    public Boolean isVisible() {
        return true;
    }
}

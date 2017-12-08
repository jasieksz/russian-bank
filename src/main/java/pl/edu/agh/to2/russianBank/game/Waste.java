package main.java.pl.edu.agh.to2.russianBank.game;

import java.util.List;

public class Waste extends ICardSet {
    private List<Card> cards;

    @Override
    Boolean tryTakeTopCard() {
        return null;
    }

    @Override
    Card takeTopCard() {
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

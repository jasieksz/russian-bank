package main.java.pl.edu.agh.to2.russianBank.game;

import java.util.List;

public class House extends ICardSet {

    private List<Card> cards;
    private Integer position;

    public House(List<Card> cards) {
        this.cards = cards;
    }

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
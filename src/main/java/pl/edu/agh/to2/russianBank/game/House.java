package main.java.pl.edu.agh.to2.russianBank.game;

import java.util.List;
import java.util.Optional;

public class House extends ICardSet {

    private List<Card> cards;
    private Integer position;

    public House(List<Card> cards) {
        this.cards = cards;
    }


    @Override
    public Optional<Card> takeTopCard() {
        return null;
    }

    @Override
    public Boolean putCard(Card card) {
        return null;

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

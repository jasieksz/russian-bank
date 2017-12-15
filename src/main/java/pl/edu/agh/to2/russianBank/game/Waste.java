package main.java.pl.edu.agh.to2.russianBank.game;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Waste extends main.java.pl.edu.agh.to2.russianBank.game.ICardSet {

    public Waste() {
        cards = Collections.emptyList();
    }

    private List<main.java.pl.edu.agh.to2.russianBank.game.Card> cards;

    @Override
    public Optional<main.java.pl.edu.agh.to2.russianBank.game.Card> takeTopCard() {
        Optional<main.java.pl.edu.agh.to2.russianBank.game.Card> result = Optional.empty();
        if(tryTakeTopCard()){
            result = Optional.of(cards.remove(cards.size() - 1));
        }
        return result;
    }

    private Boolean tryTakeTopCard(){
        return cards.size()>0;
    }

    private main.java.pl.edu.agh.to2.russianBank.game.Card lookUpTopCard(){
        return cards.get(cards.size() - 1);
    }


    private Boolean tryPutCard(main.java.pl.edu.agh.to2.russianBank.game.Card card) {
        return true;
    }

    @Override
    public Boolean putCard(main.java.pl.edu.agh.to2.russianBank.game.Card card) {
        return tryPutCard(card) && cards.add(card);
    }
    @Override
    public Integer getSize() {
        return cards.size();
    }

    @Override
    public Boolean isVisible() {
        return true;
    }
}

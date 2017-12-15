package main.java.pl.edu.agh.to2.russianBank.game;

import java.util.List;
import java.util.Optional;

public class Foundation extends main.java.pl.edu.agh.to2.russianBank.game.ICardSet { // ACE -> 2 -> 3 -> ... -> KING

    private List<main.java.pl.edu.agh.to2.russianBank.game.Card> cards;
    private Integer position;
    private main.java.pl.edu.agh.to2.russianBank.game.CardSuit suit;

    public Foundation(List<main.java.pl.edu.agh.to2.russianBank.game.Card> cards) {
        this.cards = cards;
    }

    @Override
    public Optional<main.java.pl.edu.agh.to2.russianBank.game.Card> takeTopCard() {
        Optional<main.java.pl.edu.agh.to2.russianBank.game.Card> result = Optional.empty();
        if(tryTakeTopCard()){
            result = Optional.of(cards.remove(cards.size() - 1));
        }
        return result;
    }

    @Override
    public Boolean putCard(main.java.pl.edu.agh.to2.russianBank.game.Card card) {
        return tryPutCard(card) && cards.add(card);
    }

    private Boolean tryPutCard(main.java.pl.edu.agh.to2.russianBank.game.Card card) {
        main.java.pl.edu.agh.to2.russianBank.game.Card topCard = lookUpTopCard();
        return (topCard.getSuit() == card.getSuit()) &&
                (topCard.getRank().getRank() == (card.getRank().getRank() - 1));
    }

    private Boolean tryTakeTopCard(){
        return false;
    }

    private main.java.pl.edu.agh.to2.russianBank.game.Card lookUpTopCard(){
        return cards.get(cards.size() - 1);
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

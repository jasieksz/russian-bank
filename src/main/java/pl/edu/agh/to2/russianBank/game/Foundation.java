package main.java.pl.edu.agh.to2.russianBank.game;

import java.util.List;
import java.util.Optional;

public class Foundation extends ICardSet { // ACE -> 2 -> 3 -> ... -> KING

    private List<Card> cards;
    private Integer position;
    private CardSuit suit;

    public Foundation(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public Optional<Card> takeTopCard() {
        Optional<Card> result = Optional.empty();
        if(tryTakeTopCard()){
            result = Optional.of(cards.remove(cards.size() - 1));
        }
        return result;
    }

    @Override
    public Boolean putCard(Card card) {
        return tryPutCard(card) && cards.add(card);
    }

    private Boolean tryPutCard(Card card) {
        Card topCard = lookUpTopCard();
        return (topCard.getSuit() == card.getSuit()) &&
                (topCard.getRank().getRank() == (card.getRank().getRank() - 1));
    }

    private Boolean tryTakeTopCard(){
        return false;
    }

    private Card lookUpTopCard(){
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

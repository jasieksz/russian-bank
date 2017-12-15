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
        Optional<Card> result = Optional.empty();
        if(tryTakeTopCard()){
            result = Optional.of(cards.remove(cards.size() - 1));
        }
        return result;
    }

    private Boolean tryTakeTopCard(){
        return cards.size()>0;
    }

    private Card lookUpTopCard(){
        return cards.get(cards.size() - 1);
    }


    private Boolean tryPutCard(Card card) {
        Card topCard = lookUpTopCard();
        return (topCard.getOppositeSuits().contains(card.getSuit()) &&
                (topCard.getRank().getRank() == (card.getRank().getRank() - 1)));
    }

    @Override
    public Boolean putCard(Card card) {
        return cards.add(card);
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

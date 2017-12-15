package pl.edu.agh.to2.russianBank.game;

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
        return true;
    }

    private Boolean enemyTryPutCard(Card card) {
        Card topCard = lookUpTopCard();
        return (topCard.getSuit().getSuitId().equals(card.getSuit().getSuitId()))
                && (topCard.getRank().getRank().equals(card.getRank().getRank() - 1)
                    || topCard.getRank().getRank().equals(card.getRank().getRank() + 1));
    }

    @Override
    public Boolean putCard(Card card) {
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

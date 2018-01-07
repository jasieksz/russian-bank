package pl.edu.agh.to2.russianBank.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Waste extends ICardSet {

    private Integer position;
    public Waste() {
        cards = new ArrayList<>();
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

    @Override
    public Integer getPosition() {
        return position;
    }

    @Override
    public Optional<Card> readTopCard() {
        Optional<Card> result = Optional.empty();
        if(tryTakeTopCard()){
            //czy aby na pewno ściągać tę kartę tutaj? czy tylko dowiedzieć się jaka to karta (GUI)

            result = Optional.of(cards.get(cards.size() - 1));
        }
        return result;
    }
}

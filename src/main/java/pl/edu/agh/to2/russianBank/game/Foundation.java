package pl.edu.agh.to2.russianBank.game;

import javafx.collections.ObservableList;

import java.util.List;
import java.util.Optional;

public class Foundation extends ICardSet { // ACE -> 2 -> 3 -> ... -> KING

    private ObservableList<Card> cards;
    private Integer position;


    public Foundation(ObservableList<Card> cards) {
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
        return (tryPutCard(card) && cards.add(card));
    }

    // TODO : add case when stack is empty => put card && set suit
    // TODO : when using Optional, check isPresent !
    // changed behaviour a bit so that card suit is created on empty pile, look also at putCard
    private Boolean tryPutCard(Card card) {
        if(cards.isEmpty() && card.getRank().getRank().equals(1)) return true;
        return lookUpTopCard().map(topCard -> isCardCorrect(topCard, card)).orElse(false);
    }

    private Boolean isCardCorrect(Card card1, Card card2){ // topcard, new card
        return (card1.getSuit().getSuitId().equals(card2.getSuit().getSuitId())) &&
                (card1.getRank().getRank().equals(card2.getRank().getRank() - 1));
    }

    private Boolean tryTakeTopCard(){
        return false;
    }

    public Optional<Card> lookUpTopCard(){
        if (!cards.isEmpty()){
        return Optional.ofNullable(cards.get(cards.size() - 1));
        }
        return Optional.empty();
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

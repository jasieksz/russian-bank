package pl.edu.agh.to2.russianBank.game;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Optional;

public class Foundation extends ICardSet { // ACE -> 2 -> 3 -> ... -> KING

    private ObservableList<Card> cards;
    private Integer position;


    public Foundation(List<Card> cards) {
        this.cards = FXCollections.observableList(cards);
    }

    @Override
    public Optional<Card> takeTopCard() {
        Optional<Card> result = Optional.empty();
        if (tryTakeTopCard()) {
            result = Optional.of(cards.remove(cards.size() - 1));
        }
        return result;
    }

    @Override
    public Boolean putCard(Card card) {
        return tryPutCard(card) && cards.add(card);
    }

    private Boolean tryPutCard(Card card) {
        if(cards.isEmpty() && card.getRank().getRank().equals(1)) return true;
        return lookUpTopCard().map(topCard -> isCardCorrect(topCard, card)).orElse(false);
    }

    private Boolean isCardCorrect(Card card1, Card card2){ // topcard, new card
        return (card1.getSuit().getSuitId().equals(card2.getSuit().getSuitId())) &&
                (card1.getRank().getRank().equals(card2.getRank().getRank() - 1));
    }

    private Boolean tryTakeTopCard() {
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
        if (tryTakeTopCard()) {
            //czy aby na pewno ściągać tę kartę tutaj? czy tylko dowiedzieć się jaka to karta (GUI)

            result = Optional.of(cards.get(cards.size() - 1));
        }
        return result;
    }
/*
    @Override
    public void handleMouseClicked() {
        // TODO: 2018-01-06


    }*/

    @Override
    public void addListener(ListChangeListener<Card> listener) {
        cards.addListener(listener);
    }
}

package pl.edu.agh.to2.russianBank.game;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Optional;

public class Hand extends ICardSet {

    private ObservableList<Card> cards;
    private Integer position;

    public Hand(List<Card> cards) {
        this.cards = FXCollections.observableList(cards);
    }

    // TODO should we automatically reshuffle if no more cards present?
    // TODO : PlayerDeck should be responsible for this, it contains both hand & waste @J
    @Override
    public Optional<Card> takeTopCard() { // removes top card
        return cards.size()>0 ? Optional.of(cards.remove(cards.size()-1)) : Optional.empty();
    }

    @Override
    public Boolean putCard(Card card) {
        return this.cards.add(card);
    }


    @Override
    public Integer getSize() {
        return cards.size();
    }

    @Override
    public Boolean isVisible() {
        return false;
    }

    @Override
    public Integer getPosition() {
        return position;
    }

    @Override
    public Optional<Card> readTopCard() {
        return cards.size()>0 ? Optional.of(cards.get(cards.size()-1)) : Optional.empty();
    }

    @Override
    public void handleMouseClicked() {
        // TODO: 2018-01-06
    }

    @Override
    public void addListener(ListChangeListener<Card> listener) {
        cards.addListener(listener);
    }
}

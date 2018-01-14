package pl.edu.agh.to2.russianBank.game;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import pl.edu.agh.to2.russianBank.game.command.MoveController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Hand extends ICardSet {

    private ObservableList<Card> cards;
    private int position;

    public Hand() {
        this.cards = FXCollections.observableArrayList();
    }

    public Hand(List<Card> cards) {
        this.cards = FXCollections.observableList(cards);
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public Optional<Card> takeTopCard() { // removes top card
        return cards.size() > 0 ? Optional.of(cards.remove(cards.size() - 1)) : Optional.empty();
    }

    @Override
    public Optional<Card> readTopCard() {
        return cards.size() > 0 ? Optional.of(cards.get(cards.size() - 1)) : Optional.empty();
    }

    @Override
    public boolean putCard(Card card) {
        return false;
    }

    @Override
    public boolean enemyPutCard(Card card) {
        return putCard(card);
    }

    @Override
    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public int getSize() {
        return cards.size();
    }

    @Override
    public boolean isVisible() {
        return false;
    }

    @Override
    public void addListener(ListChangeListener<Card> listener) {
        cards.addListener(listener);
    }
}

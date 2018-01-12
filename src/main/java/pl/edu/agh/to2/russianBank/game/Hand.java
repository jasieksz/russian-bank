package pl.edu.agh.to2.russianBank.game;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import pl.edu.agh.to2.russianBank.game.command.MoveController;

import java.util.List;
import java.util.Optional;

public class Hand extends ICardSet {

    private ObservableList<Card> cards;
    private int position;

    public void setMoveController(MoveController moveController) {
        this.moveController = moveController;
    }

    private MoveController moveController;

    public Hand() {
        this.cards = FXCollections.observableArrayList();
    }

    // TODO : add moveCOntroller
    public Hand(List<Card> cards) {
        this.cards = FXCollections.observableList(cards);
        //this.setPosition(0);
    }

    // TODO : swap with Waste when empty -> do we need moveController for this (TODO in PlayerDeck) @J
    @Override
    public Optional<Card> takeTopCard() { // removes top card
        return cards.size() > 0 ? Optional.of(cards.remove(cards.size() - 1)) : Optional.empty();
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
    public int getSize() {
        return cards.size();
    }

    @Override
    public boolean isVisible() {
        return false;
    }

    @Override
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public Optional<Card> readTopCard() {
        return cards.size() > 0 ? Optional.of(cards.get(cards.size() - 1)) : Optional.empty();
    }

    @Override
    public void addListener(ListChangeListener<Card> listener) {
        cards.addListener(listener);
    }
}

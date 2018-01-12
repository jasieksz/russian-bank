package pl.edu.agh.to2.russianBank.game;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import pl.edu.agh.to2.russianBank.game.command.MoveController;

import java.util.ArrayList;
import java.util.Optional;

public class Waste extends ICardSet {

    private int position;
    private ObservableList<Card> cards;
    private MoveController moveController;

    public void setMoveController(MoveController moveController) {
        this.moveController = moveController;
    }

    public Waste() {
        cards = FXCollections.observableArrayList();
    }


    private boolean tryTakeTopCard() {
        return cards.size() > 0;
    }

    private boolean tryPutCard(Card card) {
        return true;
    }


    private boolean isEnemyPutCardCorrect(Card topCard, Card card) {
        return (topCard.getSuit().getSuitId() == card.getSuit().getSuitId())
                && (topCard.getRank().getRank() == card.getRank().getRank() - 1
                || topCard.getRank().getRank() == card.getRank().getRank() + 1);
    }

    @Override
    public Optional<Card> takeTopCard() {
        return cards.size() > 0 ? Optional.of(cards.remove(cards.size() - 1)) : Optional.empty();

    }

    @Override
    public Optional<Card> readTopCard() {
        return cards.size() > 0 ? Optional.of(cards.get(cards.size() - 1)) : Optional.empty();
    }

    @Override
    public boolean putCard(Card card) {
        return tryPutCard(card) && cards.add(card);
    }

    @Override
    public boolean enemyPutCard(Card card) {
        return (readTopCard()
                .map(topCard -> isEnemyPutCardCorrect(topCard, card))
                .orElse(true) && cards.add(card));
    }

    @Override
    public int getSize() {
        return cards.size();
    }

    @Override
    public boolean isVisible() {
        return true;
    }

    @Override
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }


    @Override
    public void addListener(ListChangeListener<Card> listener) {
        cards.addListener(listener);
    }
}

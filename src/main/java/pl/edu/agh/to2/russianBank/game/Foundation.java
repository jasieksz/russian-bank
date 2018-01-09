package pl.edu.agh.to2.russianBank.game;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import pl.edu.agh.to2.russianBank.game.command.MoveController;

import java.util.List;
import java.util.Optional;

public class Foundation extends ICardSet { // ACE -> 2 -> 3 -> ... -> KING

    private ObservableList<Card> cards;
    private int position;
    private MoveController moveController;

    public Foundation(ObservableList<Card> cards, int position, MoveController moveController) {
        this.cards = cards;
        this.position = position;
        this.moveController = moveController;
    }

    public Foundation(ObservableList<Card> cards) {
        this.cards = cards;
    }

    public Foundation(List<Card> cards) {
        this(FXCollections.observableList(cards));
    }

    @Override
    public Optional<Card> takeTopCard() {
        return Optional.empty(); // always false - illeagal move
    }

    @Override
    public boolean putCard(Card card) {
        return tryPutCard(card) && cards.add(card);
    }

    private boolean tryPutCard(Card card) {
        if (cards.isEmpty() && card.getRank().getRank() == 1) return true;
        return readTopCard().map(topCard -> isCardCorrect(topCard, card)).orElse(false);
    }

    private boolean isCardCorrect(Card card1, Card card2) { // topcard, new card
        return (card1.getSuit().getSuitId() == card2.getSuit().getSuitId() &&
                (card1.getRank().getRank() == card2.getRank().getRank() - 1));
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

    @Override
    public Optional<Card> readTopCard() {
        return cards.size() > 0 ? Optional.of(cards.get(cards.size() - 1)) : Optional.empty();
    }

    @Override
    public void addListener(ListChangeListener<Card> listener) {
        cards.addListener(listener);
    }
}

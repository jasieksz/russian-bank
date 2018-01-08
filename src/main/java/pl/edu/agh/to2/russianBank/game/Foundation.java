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
        Optional<Card> result = Optional.empty();
        if (tryTakeTopCard()) {
            result = Optional.of(cards.remove(cards.size() - 1));
        }
        return result;
    }

    @Override
    public boolean putCard(Card card) {
        return tryPutCard(card) && cards.add(card);
    }

    // TODO : add case when stack is empty => put card && set suit
    // TODO : when using Optional, check isPresent !
    // changed behaviour a bit so that card suit is created on empty pile, look also at putCard
    private boolean tryPutCard(Card card) {
        if (cards.isEmpty() && card.getRank().getRank() == 1) return true;
        return lookUpTopCard().map(topCard -> isCardCorrect(topCard, card)).orElse(false);
    }

    private boolean isCardCorrect(Card card1, Card card2) { // topcard, new card
        return (card1.getSuit().getSuitId() == card2.getSuit().getSuitId() &&
                (card1.getRank().getRank() == card2.getRank().getRank() - 1));
    }

    private boolean tryTakeTopCard() {
        return false;
    }

    public Optional<Card> lookUpTopCard() {
        if (!cards.isEmpty()) {
            return Optional.ofNullable(cards.get(cards.size() - 1));
        }
        return Optional.empty();
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
        Optional<Card> result = Optional.empty();
        if (tryTakeTopCard()) {
            //czy aby na pewno ściągać tę kartę tutaj? czy tylko dowiedzieć się jaka to karta (GUI)

            result = Optional.of(cards.get(cards.size() - 1));
        }
        return result;
    }

    @Override
    public void addListener(ListChangeListener<Card> listener) {
        cards.addListener(listener);
    }
}

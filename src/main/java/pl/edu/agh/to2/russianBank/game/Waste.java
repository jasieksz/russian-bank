package pl.edu.agh.to2.russianBank.game;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import pl.edu.agh.to2.russianBank.game.command.MoveController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Waste extends ICardSet {

    private ObservableList<Card> cards;
    private int position;

    public Waste() {
        cards = FXCollections.observableArrayList();
    }

    public void setPosition(int position) {
        this.position = position;
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
        return cards.add(card);
    }

    @Override
    public boolean enemyPutCard(Card card) {
        return (readTopCard()
                .map(topCard -> isEnemyPutCardCorrect(topCard, card))
                .orElse(true) && cards.add(card));
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
        return true;
    }

    @Override
    public void addListener(ListChangeListener<Card> listener) {
        cards.addListener(listener);
    }

    private boolean isEnemyPutCardCorrect(Card topCard, Card card) {
        return (topCard.getSuit().getSuitId() == card.getSuit().getSuitId())
                && (topCard.getRank().getRank() == card.getRank().getRank() - 1
                || topCard.getRank().getRank() == card.getRank().getRank() + 1);
    }
}

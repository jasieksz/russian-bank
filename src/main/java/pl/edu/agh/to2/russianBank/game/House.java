package pl.edu.agh.to2.russianBank.game;

import com.google.common.base.MoreObjects;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class House extends ICardSet {

    private ObservableList<Card> cards;
    private int position;

    public House() {
        this.cards = FXCollections.observableArrayList();
    }

    public House(ObservableList<Card> cards, int position) {
        this.cards = cards;
        this.position = position;
    }

    public House(ObservableList<Card> cards) {
        this.cards = cards;
    }

    public House(List<Card> cards) {
        this(FXCollections.observableList(cards));
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
        return putCard(card);
    }

    @Override
    public List<Card> getCards() {
        return cards;
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
    public void addListener(ListChangeListener<Card> listener) {
        cards.addListener(listener);
    }

    private boolean tryPutCard(Card card) {
        return readTopCard().map(c -> isCardCorrect(c, card)).orElse(true);
    }

    private boolean isCardCorrect(Card card1, Card card2) { //top, new
        return (card1.getOppositeSuits().contains(card2.getSuit()) &&
                (card1.getRank().getRank() == (card2.getRank().getRank() + 1)));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        House house = (House) o;
        return position == house.position &&
                Objects.equals(cards, house.cards);
    }

    @Override
    public int hashCode() {

        return Objects.hash(cards, position);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("cards", cards)
                .add("position", position)
                .toString();
    }
}

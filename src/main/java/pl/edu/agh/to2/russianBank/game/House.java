package pl.edu.agh.to2.russianBank.game;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import pl.edu.agh.to2.russianBank.game.command.Move;
import pl.edu.agh.to2.russianBank.game.command.MoveController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class House extends ICardSet {

    private ObservableList<Card> cards;
    private int position;
    private MoveController moveController;

    public House() {
        this.cards = FXCollections.observableArrayList();
    }

    public House(ObservableList<Card> cards, int position, MoveController moveController) {
        this.cards = cards;
        this.position = position;
        this.moveController = moveController;
    }

    public House(ObservableList<Card> cards) {
        this.cards = cards;
    }

    public House(List<Card> cards) {
        this(FXCollections.observableList(cards));
    }



    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }


    private boolean tryPutCard(Card card) {
        return readTopCard().map(c -> isCardCorrect(c, card)).orElse(true);
    }

    private boolean isCardCorrect(Card card1, Card card2) { //top, new
        return (card1.getOppositeSuits().contains(card2.getSuit()) &&
                (card1.getRank().getRank() == (card2.getRank().getRank() + 1)));
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
    public int getSize() {
        return cards.size();
    }

    @Override
    public boolean isVisible() {
        return true;
    }


    public void setPosition(int p) {
         position = p;
    }

    @Override
    public int getPosition() {
        return position;
    }


    @Override
    public void addListener(ListChangeListener<Card> listener) {
        cards.addListener(listener);
    }
}

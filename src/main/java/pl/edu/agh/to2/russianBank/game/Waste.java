package pl.edu.agh.to2.russianBank.game;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.Optional;

public class Waste extends ICardSet {

    private int position;
    private ObservableList<Card> cards;

    public Waste() {
        cards = FXCollections.observableArrayList();
    }

    @Override
    public Optional<Card> takeTopCard() {
        Optional<Card> result = Optional.empty();
        if(tryTakeTopCard()){
            result = Optional.of(cards.remove(cards.size() - 1));
        }
        return result;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    private boolean tryTakeTopCard(){
        return cards.size()>0;
    }

    private Card lookUpTopCard(){
        return cards.get(cards.size() - 1);
    }


    private boolean tryPutCard(Card card) {
        return true;
    }

    private boolean enemyTryPutCard(Card card) {
        Card topCard = lookUpTopCard();
        return (topCard.getSuit().getSuitId() == card.getSuit().getSuitId())
                && (topCard.getRank().getRank() == card.getRank().getRank() - 1
                || topCard.getRank().getRank() == card.getRank().getRank() + 1);
    }

    @Override
    public boolean putCard(Card card) {
        return tryPutCard(card) && cards.add(card);
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
        if(tryTakeTopCard()){
            //czy aby na pewno ściągać tę kartę tutaj? czy tylko dowiedzieć się jaka to karta (GUI)

            result = Optional.of(cards.get(cards.size() - 1));
        }
        return result;
    }

   /* @Override
    public void handleMouseClicked() {
        // TODO: 2018-01-06
    }*/

    @Override
    public void addListener(ListChangeListener<Card> listener) {
        cards.addListener(listener);
    }
}

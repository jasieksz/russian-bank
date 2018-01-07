package pl.edu.agh.to2.russianBank.game;

import javafx.collections.ObservableList;

import java.util.List;
import java.util.Optional;

public class House extends ICardSet {

    private ObservableList<Card> cards;
    private Integer position;

    public House(ObservableList<Card> cards) {
        this.cards = cards;
    }

    @Override
    public Optional<Card> takeTopCard() {
        Optional<Card> result = Optional.empty();
        if(tryTakeTopCard()){
            result = Optional.of(cards.remove(cards.size() - 1));
        }
        return result;
    }

    private Boolean tryTakeTopCard(){
        return cards.size()>0;
    }

    private Optional<Card> lookUpTopCard(){
        return Optional.ofNullable(cards.get(cards.size() - 1));
    }


    // TODO : optional isPresent!!
    //
    private Boolean tryPutCard(Card card) {
        return lookUpTopCard().map(c -> isCardCorrect(c, card)).orElse(true);
    }

    private boolean isCardCorrect (Card card1, Card card2){ //top, new
        return (card1.getOppositeSuits().contains(card2.getSuit()) &&
                (card1.getRank().getRank() == (card2.getRank().getRank() - 1)));
    }

    @Override
    public Boolean putCard(Card card) {
        return cards.add(card);
    }

    @Override
    public Integer getSize() {
        return cards.size();
    }

    @Override
    public Boolean isVisible() {
        return true;
    }

    @Override
    public Integer getPosition() {
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
}

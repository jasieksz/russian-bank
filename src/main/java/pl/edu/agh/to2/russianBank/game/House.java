package pl.edu.agh.to2.russianBank.game;

import java.util.List;
import java.util.Optional;

public class House extends ICardSet {

    private List<Card> cards;
    private Integer position;

    public House(List<Card> cards) {
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

    private Boolean tryPutCard(Card card) {
        Optional<Card> topCard = lookUpTopCard();
        return (topCard.get().getOppositeSuits().contains(card.getSuit()) &&
                (topCard.get().getRank().getRank() == (card.getRank().getRank() - 1)));
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

    public Integer getPosition() {
        return position;
    }
}

package pl.edu.agh.to2.russianBank.game;


import java.util.List;
import java.util.Optional;

public abstract class ICardSet {
    private List<Card> cards;
    public abstract Optional<Card> takeTopCard();
    public abstract Boolean putCard(Card card);
    public abstract Integer getSize();
    public abstract Boolean isVisible();
    public abstract Integer getPosition();
    public abstract Optional<Card> readTopCard();
}

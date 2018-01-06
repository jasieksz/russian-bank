package pl.edu.agh.to2.russianBank.game;


import javafx.collections.ListChangeListener;

import java.util.Optional;

public abstract class ICardSet {
    public abstract Optional<Card> takeTopCard();
    public abstract Boolean putCard(Card card);
    public abstract Integer getSize();
    public abstract Boolean isVisible();
    public abstract Integer getPosition();
    public abstract Optional<Card> readTopCard();
    public abstract void addListener(ListChangeListener<Card> listener);
}

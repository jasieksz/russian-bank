package pl.edu.agh.to2.russianBank.game;


import javafx.collections.ObservableList;

import java.util.List;
import java.util.Observable;
import java.util.Optional;

public abstract class ICardSet {
    private ObservableList<Card> cards;
    public abstract Optional<Card> takeTopCard();
    public abstract Boolean putCard(Card card);
    public abstract Integer getSize();
    public abstract Boolean isVisible();
    public abstract Integer getPosition();
    public abstract Optional<Card> readTopCard();
}

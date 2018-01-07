package pl.edu.agh.to2.russianBank.game;


import javafx.collections.ListChangeListener;

import java.util.Optional;

public abstract class ICardSet {

    private ICardSet firstChosenCard;
    private Boolean firstChosen = false;
    public abstract Optional<Card> takeTopCard();

    public abstract Boolean putCard(Card card);

    public abstract Integer getSize();

    public abstract Boolean isVisible();

    public abstract Integer getPosition();

    public abstract Optional<Card> readTopCard();

    public abstract void addListener(ListChangeListener<Card> listener);

    public abstract boolean validateMove();

    public void makeMove(ICardSet source) {
        if (validateMove()) {
            source.readTopCard().ifPresent(card -> {
                if (putCard(card)) {
                    source.takeTopCard();
                }
            });
        }
    }
}

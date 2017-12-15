package main.java.pl.edu.agh.to2.russianBank.game;


import java.util.List;
import java.util.Optional;

public abstract class ICardSet {
    private List<main.java.pl.edu.agh.to2.russianBank.game.Card> cards;
    public abstract Optional<main.java.pl.edu.agh.to2.russianBank.game.Card> takeTopCard();
    public abstract Boolean putCard(main.java.pl.edu.agh.to2.russianBank.game.Card card);
    public abstract Integer getSize();
    public abstract Boolean isVisible();
}

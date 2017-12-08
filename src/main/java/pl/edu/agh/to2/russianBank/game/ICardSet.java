package main.java.pl.edu.agh.to2.russianBank.game;


import java.util.ArrayList;
import java.util.List;

abstract class ICardSet {
    private List<Card> cards;
    abstract Boolean tryTakeTopCard();
    abstract Card takeTopCard();
    abstract void putCard();
    abstract Integer getSize();
    abstract Boolean isVisible();
}

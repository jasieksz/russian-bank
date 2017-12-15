package main.java.pl.edu.agh.to2.russianBank.game;

import java.util.Collections;
import java.util.List;

public class Card {
    private main.java.pl.edu.agh.to2.russianBank.game.CardSuit suit;
    private main.java.pl.edu.agh.to2.russianBank.game.CardRank rank;

    public Card(main.java.pl.edu.agh.to2.russianBank.game.CardSuit suit, main.java.pl.edu.agh.to2.russianBank.game.CardRank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public List<main.java.pl.edu.agh.to2.russianBank.game.CardSuit> getOppositeSuits(){
        List ret = Collections.emptyList();
        ret.add(main.java.pl.edu.agh.to2.russianBank.game.CardSuit.values()[(this.suit.ordinal()+1)%4]);
        ret.add(main.java.pl.edu.agh.to2.russianBank.game.CardSuit.values()[(this.suit.ordinal()+3)%4]);
        return ret;
    }

    public main.java.pl.edu.agh.to2.russianBank.game.CardSuit getSuit() {
        return suit;
    }

    public main.java.pl.edu.agh.to2.russianBank.game.CardRank getRank() {
        return rank;
    }
}

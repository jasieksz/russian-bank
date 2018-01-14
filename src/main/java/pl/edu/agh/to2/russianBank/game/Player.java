package pl.edu.agh.to2.russianBank.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {

    private String name;
    private PlayerDeck playerDeck;

    public Player (String name){
        this.name = name;
        List<Card> tmp = new ArrayList<>();
        for (CardSuit suit : CardSuit.values()) {
            for (CardRank rank : CardRank.values()) {
                tmp.add(new Card(suit, rank));
            }
        }
        Collections.shuffle(tmp);
        this.playerDeck = new PlayerDeck(new Hand(tmp), new Waste());
    }


    public Player(String name, PlayerDeck playerDeck) {
        this.name = name;
        this.playerDeck = playerDeck;
    }

    public String getName() {
        return name;
    }

    public PlayerDeck getPlayerDeck() {
        return playerDeck;
    }
}

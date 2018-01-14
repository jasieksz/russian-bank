package pl.edu.agh.to2.russianBank.game;

import com.google.common.base.MoreObjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name) &&
                Objects.equals(playerDeck, player.playerDeck);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, playerDeck);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("playerDeck", playerDeck)
                .toString();
    }
}

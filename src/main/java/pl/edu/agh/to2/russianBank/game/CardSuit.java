package pl.edu.agh.to2.russianBank.game;

import com.google.common.base.MoreObjects;

public enum CardSuit {
    HEARTS(0, "S"),
    CLUBS(1, "T"),
    DIAMONDS(2, "K"),
    SPADES(3, "W");

    private int suitId;
    private String rank;

    CardSuit(int suitId, String rank) {
        this.suitId = suitId;
        this.rank = rank;
    }

    public int getSuitId() {
        return suitId;
    }

    public String getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("suitId", suitId)
                .add("rank", rank)
                .toString();
    }
}

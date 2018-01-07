package pl.edu.agh.to2.russianBank.game;

public enum CardSuit {
    HEARTS(0, "S"),
    CLUBS(1, "T"),
    DIAMONDS(2, "K"),
    SPADES(3, "W");

    private Integer suitId;
    private String rank;

    CardSuit(Integer suitId, String rank) {
        this.suitId = suitId;
        this.rank = rank;
    }
    public Integer getSuitId() {
        return suitId;
    }

    public String getRank() {
        return rank;
    }
}
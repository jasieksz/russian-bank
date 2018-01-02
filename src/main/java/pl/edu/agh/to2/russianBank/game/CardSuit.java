package pl.edu.agh.to2.russianBank.game;

public enum CardSuit {
    HEARTS(0),
    CLUBS(1),
    DIAMONDS(2),
    SPADES(3);

    public Integer getSuitId() {
        return suitId;
    }

    private Integer suitId;

    CardSuit(Integer suitId) {
        this.suitId = suitId;
    }
}

package pl.edu.agh.to2.russianBank.game;

public enum CardRank {
    CARD_2(2),
    CARD_3(3),
    CARD_4(4),
    CARD_5(5),
    CARD_6(6),
    CARD_7(7),
    CARD_8(8),
    CARD_9(9),
    JACK(10),
    QUEEN(11),
    KING(12),
    ACE(1);

    private Integer rank;
    CardRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getRank() {
        return rank;
    }
}

package pl.edu.agh.to2.russianBank.game;

import com.google.common.base.MoreObjects;

public enum CardRank {
    CARD_2(2),
    CARD_3(3),
    CARD_4(4),
    CARD_5(5),
    CARD_6(6),
    CARD_7(7),
    CARD_8(8),
    CARD_9(9),
    CARD_10(10),
    JACK(11, "J"),
    QUEEN(12, "Q"),
    KING(13, "K"),
    ACE(1, "AS");

    public int rank;
    private String rankName;

    CardRank(int rank) {
        this(rank, String.valueOf(rank));
    }

    CardRank(int rank, String rankName) {
        this.rank = rank;
        this.rankName = rankName;
    }

    public int getRank() {
        return rank;
    }

    public String getRankName() {
        return rankName;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("rank", rank)
                .add("rankName", rankName)
                .toString();
    }
}

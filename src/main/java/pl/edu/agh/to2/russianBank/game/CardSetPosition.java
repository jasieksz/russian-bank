package pl.edu.agh.to2.russianBank.game;

import com.google.common.base.MoreObjects;

public enum CardSetPosition {

    HAND_1(0),
    WASTE_1(1),
    HAND_2(2),
    WASTE_2(3),
    HOUSE_1(4),
    HOUSE_2(5),
    HOUSE_3(6),
    HOUSE_4(7),
    HOUSE_5(8),
    HOUSE_6(9),
    HOUSE_7(10),
    HOUSE_8(11),
    FOUNDATION_1(12),
    FOUNDATION_2(13),
    FOUNDATION_3(14),
    FOUNDATION_4(15),
    FOUNDATION_5(16),
    FOUNDATION_6(17),
    FOUNDATION_7(18),
    FOUNDATION_8(19);

    private int position;

    CardSetPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("position", position)
                .toString();
    }
}

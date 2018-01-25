package pl.edu.agh.to2.russianBank.game.command;

import com.google.common.base.MoreObjects;

public enum MoveCodes {
    WIN(3),
    SWAP(2),
    ACC(1),
    REJ(0);

    private int code;

    MoveCodes(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("code", code)
                .toString();
    }
}

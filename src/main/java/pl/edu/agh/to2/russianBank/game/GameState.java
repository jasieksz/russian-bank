package pl.edu.agh.to2.russianBank.game;

import com.google.common.base.MoreObjects;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class GameState {
    private Player playerOne;
    private Player playerTwo;

    public GameState(Player playerA, Player playerB) {
        this.playerOne = playerA;
        this.playerTwo = playerB;
    }

    public List<Player> getPlayers() {
        return Arrays.asList(playerOne, playerTwo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameState gameState = (GameState) o;
        return Objects.equals(playerOne, gameState.playerOne) &&
                Objects.equals(playerTwo, gameState.playerTwo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerOne, playerTwo);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("playerA", playerOne)
                .add("playerB", playerTwo)
                .toString();
    }
}

package pl.edu.agh.to2.russianBank.game;

import com.google.common.base.MoreObjects;

import java.util.List;
import java.util.Objects;

public class GameState {

    private List<Player> players;
    private GameTable gameTable;

    public GameState(List<Player> players, GameTable gameTable) {
        this.players = players;
        this.gameTable = gameTable;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public GameTable getGameTable() {
        return gameTable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameState gameState = (GameState) o;
        return Objects.equals(players, gameState.players) &&
                Objects.equals(gameTable, gameState.gameTable);
    }

    @Override
    public int hashCode() {

        return Objects.hash(players, gameTable);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("players", players)
                .add("gameTable", gameTable)
                .toString();
    }
}

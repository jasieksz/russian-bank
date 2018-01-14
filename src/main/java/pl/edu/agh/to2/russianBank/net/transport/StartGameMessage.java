package pl.edu.agh.to2.russianBank.net.transport;

import com.google.common.base.MoreObjects;
import pl.edu.agh.to2.russianBank.game.GameState;
import pl.edu.agh.to2.russianBank.game.command.MoveController;

import java.util.Objects;

/**
 * Created by Marek on 09.01.2018.
 */
public class StartGameMessage extends Message {
    private GameState gameState;
    private MoveController moveController;

    public StartGameMessage(GameState gameState, MoveController moveController) {
        this.gameState = gameState;
        this.moveController = moveController;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public GameState getGameState() {

        return gameState;
    }

    public MoveController getMoveController() {
        return moveController;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StartGameMessage that = (StartGameMessage) o;
        return Objects.equals(gameState, that.gameState) &&
                Objects.equals(moveController, that.moveController);
    }

    @Override
    public int hashCode() {

        return Objects.hash(gameState, moveController);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("gameState", gameState)
                .add("moveController", moveController)
                .toString();
    }

    @Override
    public void accept(MessageVisitor visitor) {
        visitor.visit(this);
    }
}

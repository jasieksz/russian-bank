package pl.edu.agh.to2.russianBank.net.transport;

import pl.edu.agh.to2.russianBank.game.GameState;
import pl.edu.agh.to2.russianBank.game.command.MoveController;

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

        return gameState != null ? gameState.equals(that.gameState) : that.gameState == null;
    }

    @Override
    public int hashCode() {
        return gameState != null ? gameState.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "StartGameMessage{" +
                "gameState=" + gameState +
                '}';
    }

    @Override
    public void accept(MessageVisitor visitor) {
        visitor.visit(this);
    }
}

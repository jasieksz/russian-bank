package pl.edu.agh.to2.russianBank.net.transport;

import pl.edu.agh.to2.russianBank.game.Game;
import pl.edu.agh.to2.russianBank.game.GameTable;

/**
 * Created by Marek on 09.01.2018.
 */
public class StartGameMessage extends Message {
    private Game game;

    public StartGameMessage(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StartGameMessage that = (StartGameMessage) o;

        return game != null ? game.equals(that.game) : that.game == null;
    }

    @Override
    public int hashCode() {
        return game != null ? game.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "StartGameMessage{" +
                "game=" + game +
                '}';
    }

    @Override
    public void accept(MessageVisitor visitor) {
        visitor.visit(this);
    }
}

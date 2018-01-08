package pl.edu.agh.to2.russianBank.net.transport;

import com.google.common.base.MoreObjects;
import pl.edu.agh.to2.russianBank.game.command.Move;

import java.util.Objects;

public class MoveMessage extends Message {
    private Move move;

    public MoveMessage(Move move) {
        this.move = move;
    }

    public Move getMove() {
        return move;
    }

    public void setMove(Move move) {
        this.move = move;
    }

    @Override
    public void accept(MessageVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoveMessage that = (MoveMessage) o;
        return Objects.equals(move, that.move);
    }

    @Override
    public int hashCode() {

        return Objects.hash(move);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("move", move)
                .toString();
    }
}

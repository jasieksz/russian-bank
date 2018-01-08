package pl.edu.agh.to2.russianBank.game.command;

import com.google.common.base.MoreObjects;
import pl.edu.agh.to2.russianBank.game.Card;
import pl.edu.agh.to2.russianBank.game.GameTable;

import java.util.Objects;
import java.util.Optional;

public class Move implements Command {
    private int source;
    private int target;

    // TODO : Do we need to know which player is making move?

    public Move(int source, int target) {
        this.source = source;
        this.target = target;
    }

    @Override
    public Optional<Card> execute(GameTable gameTable) {
        Optional<Card> result = gameTable.getPiles().get(source).takeTopCard();
        result.ifPresent(card -> gameTable.getPiles().get(target).putCard(card));
        return result;
    }

    public void redo(GameTable gameTable) {
        gameTable.getPiles().get(target).putCard(gameTable.getPiles().get(source).takeTopCard().get());
    }

    public void undo(GameTable gameTable) {
        gameTable.getPiles().get(source).putCard(gameTable.getPiles().get(target).takeTopCard().get());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Move move = (Move) o;
        return source == move.source &&
                target == move.target;
    }

    @Override
    public int hashCode() {

        return Objects.hash(source, target);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("source", source)
                .add("target", target)
                .toString();
    }
}

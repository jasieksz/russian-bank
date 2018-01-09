package pl.edu.agh.to2.russianBank.game.command;

import com.google.common.base.MoreObjects;
import pl.edu.agh.to2.russianBank.game.Card;
import pl.edu.agh.to2.russianBank.game.GameTable;
import pl.edu.agh.to2.russianBank.game.ICardSet;

import java.util.Objects;
import java.util.Optional;

public class Move implements Command {
    private ICardSet source;
    private ICardSet target;

    public Move(ICardSet source, ICardSet target) {
        this.source = source;
        this.target = target;
    }

    @Override
    public Optional<Card> execute(GameTable gameTable) {
        Optional<Card> result = source.takeTopCard();
        result.ifPresent(card -> target.putCard(card));
        return result;
    }

    public void redo(GameTable gameTable) {
        target.putCard(source.takeTopCard().get()); // TODO : isPresent() missing warning
    }

    public void undo(GameTable gameTable) {
        source.putCard(target.takeTopCard().get()); // TODO : isPresent() missing warning
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

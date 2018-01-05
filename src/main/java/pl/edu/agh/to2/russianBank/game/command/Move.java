package pl.edu.agh.to2.russianBank.game.command;

import pl.edu.agh.to2.russianBank.game.Card;
import pl.edu.agh.to2.russianBank.game.ICardSet;

import java.util.Optional;

public class Move implements Command {
    private ICardSet source;
    private ICardSet target;

    // TODO : Do we need to know which player is making move?

    public Move(ICardSet source, ICardSet target) {
        this.source = source;
        this.target = target;
    }

    @Override
    public Optional<Card> execute() {
        Optional<Card> result = source.takeTopCard();
        result.ifPresent(card -> target.putCard(card));
        return result;
    }

    public void redo () {
        target.putCard(source.takeTopCard().get());
    }

    public void undo () {
        source.putCard(target.takeTopCard().get());
    }

}

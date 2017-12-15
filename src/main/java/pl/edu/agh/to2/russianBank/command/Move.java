package main.java.pl.edu.agh.to2.russianBank.command;

import main.java.pl.edu.agh.to2.russianBank.game.Card;
import main.java.pl.edu.agh.to2.russianBank.game.ICardSet;

import java.util.List;
import java.util.Optional;

public class Move implements Command {
    ICardSet source;
    ICardSet target;

    public Move(ICardSet source, ICardSet target) {
        this.source = source;
        this.target = target;
    }

    @Override
    public Optional<Card> execute() {
        Optional<Card> result = source.takeTopCard();
        if (result.isPresent()) {
            target.putCard(result.get());
        }
        return result;
    }

    public void redo () {
        target.putCard(source.takeTopCard().get());
    }

    public void undo () {
        source.putCard(target.takeTopCard().get());
    }
}

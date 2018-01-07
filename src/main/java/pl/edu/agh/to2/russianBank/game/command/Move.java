package pl.edu.agh.to2.russianBank.game.command;

import pl.edu.agh.to2.russianBank.game.Card;
import pl.edu.agh.to2.russianBank.game.GameTable;
import pl.edu.agh.to2.russianBank.game.ICardSet;

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

    public void redo (GameTable gameTable) {
        gameTable.getPiles().get(target).putCard(gameTable.getPiles().get(source).takeTopCard().get());
    }

    public void undo (GameTable gameTable) {
        gameTable.getPiles().get(source).putCard(gameTable.getPiles().get(target).takeTopCard().get());
    }

}

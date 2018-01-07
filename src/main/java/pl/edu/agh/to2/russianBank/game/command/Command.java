package pl.edu.agh.to2.russianBank.game.command;

import pl.edu.agh.to2.russianBank.game.Card;
import pl.edu.agh.to2.russianBank.game.GameTable;

import java.util.Optional;

public interface Command {
    Optional<Card> execute(GameTable gameTable);
    void redo(GameTable gameTable);
    void undo(GameTable gameTable);
}

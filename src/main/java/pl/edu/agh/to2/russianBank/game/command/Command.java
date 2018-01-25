package pl.edu.agh.to2.russianBank.game.command;

import pl.edu.agh.to2.russianBank.game.GameTable;

public interface Command {
    int execute(GameTable gameTable);

    void redo(GameTable gameTable);

    void undo(GameTable gameTable);
}

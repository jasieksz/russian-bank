package pl.edu.agh.to2.russianBank.game.command;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.edu.agh.to2.russianBank.game.GameTable;
import pl.edu.agh.to2.russianBank.ui.controllers.Service;

public class MoveController {
    private GameTable gameTable;
    private ObservableList<Command> commandStack = FXCollections
            .observableArrayList();
    private ObservableList<Command> unmadeCommandStack = FXCollections
            .observableArrayList();

    public MoveController(GameTable gameTable) {
        this.gameTable = gameTable;
    }

    public boolean executeCommand(Command command) {
        if (command.execute(gameTable)) {
            commandStack.add(command);
            unmadeCommandStack.clear();
            return true;
        }
        return false;
    }

    public void redo() {
        if (!unmadeCommandStack.isEmpty()) {
            Command command = unmadeCommandStack.remove(unmadeCommandStack.size() - 1);
            command.redo(gameTable);
            commandStack.add(command);
        }
    }

    public void undo() {
        if (!commandStack.isEmpty()) {
            Command command = commandStack.remove(commandStack.size() - 1);
            command.undo(gameTable);
            unmadeCommandStack.add(command);
        }
    }

    public ObservableList<Command> getCommandStack() {
        return commandStack;
    }

}


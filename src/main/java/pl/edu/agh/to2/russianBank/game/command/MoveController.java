package pl.edu.agh.to2.russianBank.game.command;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MoveController {
    private ObservableList<Command> commandStack = FXCollections
            .observableArrayList();

    private ObservableList<Command> unmadeCommandStack = FXCollections
            .observableArrayList();

    public void executeCommand(Command command) {
        if (command.execute().isPresent()) {
            commandStack.add(command);
            unmadeCommandStack.clear();
        }
    }

    public void redo() {
        if(!unmadeCommandStack.isEmpty()){
            Command command = unmadeCommandStack.remove(unmadeCommandStack.size()-1);
            command.redo();
            commandStack.add(command);
        }
    }

    public void undo() {
        if (!commandStack.isEmpty()){
            Command command = commandStack.remove(commandStack.size()-1);
            command.undo();
            unmadeCommandStack.add(command);
        }
    }

    public ObservableList<Command> getCommandStack() {
        return commandStack;
    }

}


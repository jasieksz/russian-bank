package main.java.pl.edu.agh.to2.russianBank.command;

import main.java.pl.edu.agh.to2.russianBank.game.Card;

import java.util.Optional;

public interface Command {
    Optional<Card> execute();
    void redo();
    void undo();
}

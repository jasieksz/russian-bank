package pl.edu.agh.to2.russianBank.game;


import javafx.collections.ListChangeListener;
import pl.edu.agh.to2.russianBank.game.command.Move;
import pl.edu.agh.to2.russianBank.game.command.MoveController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class ICardSet {

    public ICardSet() {
    }

    public abstract Optional<Card> takeTopCard();

    public abstract Optional<Card> readTopCard();

    public abstract boolean putCard(Card card);

    public abstract boolean enemyPutCard(Card card);

    public abstract List<Card> getCards();

    public abstract int getPosition();

    public abstract int getSize();

    public abstract boolean isVisible();

    public abstract void addListener(ListChangeListener<Card> listener);

    public boolean makeMove(ICardSet source, MoveController moveController) {
        return moveController.executeCommand(new Move(source, this));
    }
}

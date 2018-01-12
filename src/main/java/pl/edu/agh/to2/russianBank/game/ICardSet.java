package pl.edu.agh.to2.russianBank.game;


import javafx.collections.ListChangeListener;
import pl.edu.agh.to2.russianBank.game.command.Move;
import pl.edu.agh.to2.russianBank.game.command.MoveController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class ICardSet {
    private MoveController moveController = new MoveController(null);
    private ICardSet firstChosenCard;
    private boolean firstChosen = false;

    public abstract Optional<Card> takeTopCard();

    public abstract boolean putCard(Card card);

    public abstract boolean enemyPutCard(Card card);



    public List<Card> getCards() {return new ArrayList<>();}
    public abstract int getSize();

    public abstract boolean isVisible();

    public abstract int getPosition();

    public abstract Optional<Card> readTopCard();

    public abstract void addListener(ListChangeListener<Card> listener);

    public boolean makeMove(ICardSet source) {
        return moveController.executeCommand(new Move(source, this));
    }
}

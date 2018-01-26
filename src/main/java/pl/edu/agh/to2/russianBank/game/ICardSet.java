package pl.edu.agh.to2.russianBank.game;


import javafx.collections.ListChangeListener;
import pl.edu.agh.to2.russianBank.game.command.Move;
import pl.edu.agh.to2.russianBank.game.command.MoveController;

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

    public int makeMove(ICardSet source, MoveController moveController) {
        // TODO : TEST THIS!!!
        Move move = new Move(source.getPosition(), this.getPosition());
        //List<Integer> obligatoryMoveSources = moveController.getObligatoryMoves(moveController.getGameTable(), move);
        int executeResult = moveController.executeCommand(move);

        /*if (executeResult && obligatoryMoveSources.contains(source.getPosition()) && this.getPosition() < CardSetPosition.FOUNDATION_1.getPosition()){
            moveController.undo();
            return false;
        }*/
        return executeResult;
    }
}

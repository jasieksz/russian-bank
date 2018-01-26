package pl.edu.agh.to2.russianBank.game;


import javafx.collections.ListChangeListener;
import pl.edu.agh.to2.russianBank.game.command.Move;
import pl.edu.agh.to2.russianBank.game.command.MoveCodes;
import pl.edu.agh.to2.russianBank.game.command.MoveController;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

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
        Move move = new Move(source.getPosition(), this.getPosition());
        List<Integer> obligatoryMoveSources = moveController.getObligatoryMoves(moveController.getGameTable());
        System.out.println(obligatoryMoveSources);

        if (obligatoryMoveSources.isEmpty()){
            return moveController.executeCommand(move);
        } else {
            if (obligatoryMoveSources.contains(source.getPosition())){
                if (this.getPosition() < CardSetPosition.FOUNDATION_1.getPosition()){
                    return MoveCodes.REJ.getCode();
                }
                return moveController.executeCommand(move);
            } else {
                return MoveCodes.REJ.getCode();
            }
        }
    }
}

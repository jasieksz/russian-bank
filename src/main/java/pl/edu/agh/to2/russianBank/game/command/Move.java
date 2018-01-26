package pl.edu.agh.to2.russianBank.game.command;

import com.google.common.base.MoreObjects;
import pl.edu.agh.to2.russianBank.game.*;
import pl.edu.agh.to2.russianBank.ui.controllers.Service;

import java.util.*;
import java.util.function.Predicate;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Move implements Command {

    private int source;
    private int target;

    public Move(int source, int target) {
        this.source = source;
        this.target = target;
    }

    public ICardSet getSource(GameTable gameTable) {
        return gameTable.getPiles().stream()
                .filter(s -> s.getPosition() == this.source)
                .findFirst()
                .get();
    }

    public ICardSet getTarget(GameTable gameTable) {
        return gameTable.getPiles().stream()
                .filter(t -> t.getPosition() == this.target)
                .findFirst()
                .get();
    }

    @Override
    public int execute(GameTable gameTable) {
        ICardSet source = getSource(gameTable);
        ICardSet target = getTarget(gameTable);

        int sourcePos = source.getPosition();
        int targetPos = target.getPosition();

        boolean result = false;

        if (myHandToMyWaste().test(this)) {
            result = source.readTopCard()
                    .map(target::putCard)
                    .orElse(false);
        } else if (otherToAnyWaste().test(this)) {
            result = source.readTopCard()
                    .map(target::enemyPutCard)
                    .orElse(false);
        } else {
            result = source.readTopCard()
                    .map(target::putCard)
                    .orElse(false);
        }

        if (result) {
            source.takeTopCard();
        }

        if (emptyHand(gameTable).test(this)) {
            if (isCorrespondingWasteEmpty(gameTable).test(this)){
                return MoveCodes.WIN.getCode();
            }
            else if (gameTable.swapHandWaste(sourcePos, sourcePos + 1)){
                return MoveCodes.SWAP.getCode();
            }
        }

        org.apache.logging.log4j.Logger LOG = org.apache.logging.log4j.LogManager.getLogger();
        LOG.debug(gameTable.getPiles().get(source.getPosition()));
        LOG.debug(gameTable.getPiles().get(target.getPosition()));

        return result ? MoveCodes.ACC.getCode() : MoveCodes.REJ.getCode();
    }


    public void redo(GameTable gameTable) {
        getTarget(gameTable).getCards().add(getSource(gameTable).getCards().remove(getSource(gameTable).getCards().size()-1));
    }

    public void undo(GameTable gameTable) {
        getSource(gameTable).getCards().add(getTarget(gameTable).getCards().remove(getTarget(gameTable).getCards().size()-1));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Move move = (Move) o;
        return source == move.source &&
                target == move.target;
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, target);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("source", source)
                .add("target", target)
                .toString();
    }

    private static Predicate<Move> myHandToMyWaste() {
        return move -> (move.source == CardSetPosition.HAND_1.getPosition()
                    && move.target == CardSetPosition.WASTE_1.getPosition())
                || (move.source == CardSetPosition.HAND_2.getPosition()
                && move.target == CardSetPosition.WASTE_2.getPosition());
    }

    private static Predicate<Move> otherToAnyWaste() {
        return move -> (move.target == CardSetPosition.WASTE_1.getPosition()
                || move.target == CardSetPosition.WASTE_2.getPosition());

    }

    private static Predicate<Move> emptyHand(GameTable gameTable) {
        return move -> ((move.source == CardSetPosition.HAND_1.getPosition()
                || move.source == CardSetPosition.HAND_2.getPosition())
                && move.getSource(gameTable).getSize() == 0);
    }

    private static Predicate<Move> isCorrespondingWasteEmpty(GameTable gameTable) {
        return move ->
                (gameTable.getPiles()
                .get(move.source + 1)
                .getSize() == 0);
    }
}

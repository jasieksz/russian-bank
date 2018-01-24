package pl.edu.agh.to2.russianBank.game.command;

import com.google.common.base.MoreObjects;
import pl.edu.agh.to2.russianBank.game.*;
import pl.edu.agh.to2.russianBank.ui.controllers.Service;

import java.util.*;
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
// TODO change move
    public ICardSet getTarget(GameTable gameTable) {
        return gameTable.getPiles().stream()
                .filter(t -> t.getPosition() == this.target)
                .findFirst()
                .get();
    }

    @Override
    public boolean execute(GameTable gameTable) {
        ICardSet source = getSource(gameTable);
        ICardSet target = getTarget(gameTable);

        int sourcePos = source.getPosition();
        int targetPos = target.getPosition();

        boolean result = false;

        if ((sourcePos == CardSetPosition.HAND_1.getPosition() && targetPos == CardSetPosition.WASTE_1.getPosition())
                || (sourcePos == CardSetPosition.HAND_2.getPosition() && targetPos == CardSetPosition.WASTE_2.getPosition())) { // MY HAND -> MY WASTE
            result = source.readTopCard().map(c -> target.putCard(c)).orElse(false);
        } else if (targetPos == CardSetPosition.WASTE_1.getPosition() || targetPos == CardSetPosition.WASTE_2.getPosition()) { // OTHER -> WASTE
            result = source.readTopCard().map(c -> target.enemyPutCard(c)).orElse(false);
        } else { // OTHER -> OTHER
            result = source.readTopCard().map(c -> target.putCard(c)).orElse(false);
        }

        if (result) {
            source.takeTopCard();
        }

        // TODO : can we move this entire if outside of Move? @J
        if ((sourcePos == CardSetPosition.HAND_1.getPosition() || sourcePos == CardSetPosition.HAND_2.getPosition()) && source.getSize() == 0) {
            if (isHandEmpty(gameTable, sourcePos)) {
                Service.getInstance().getClient().endGame(true, "winning condition");
            } else {
                Service.getInstance().getClient().swapHandWaste(sourcePos, sourcePos + 1);
            }
        }
        org.apache.logging.log4j.Logger LOG = org.apache.logging.log4j.LogManager.getLogger();

        LOG.debug(gameTable.getPiles().get(source.getPosition()));
        LOG.debug(gameTable.getPiles().get(target.getPosition()));

        return result;
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

    private boolean isHandEmpty(GameTable gameTable, int sourcePos) {
        Waste waste = gameTable.getPlayersCard().stream()
                .filter(pD -> pD.getHand().getPosition() == sourcePos)
                .map(pD -> pD.getWaste()).findFirst().get();

        if (gameTable.getPlayersCard().stream()
                .filter(pD -> pD.getHand().getPosition() == sourcePos)
                .allMatch(pD -> pD.getWaste().getSize() == 0)) {
            return true;
        } else {
            gameTable.swapPiles(getSource(gameTable), waste);
            return false;
        }
    }
}

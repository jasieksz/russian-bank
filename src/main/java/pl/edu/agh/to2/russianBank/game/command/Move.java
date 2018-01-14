package pl.edu.agh.to2.russianBank.game.command;

import com.google.common.base.MoreObjects;
import pl.edu.agh.to2.russianBank.game.*;
import pl.edu.agh.to2.russianBank.ui.controllers.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Move implements Command {
    private ICardSet source;
    private ICardSet target;

    public Move(ICardSet source, ICardSet target) {
        this.source = source;
        this.target = target;
    }

    @Override
    public boolean execute(GameTable gameTable) {

        int sourcePos = source.getPosition();
        int targetPos = target.getPosition();

        boolean result = false;
        if (!noObligatoryMoveExists(gameTable, source)) {
            return false;
        }

        if ((sourcePos == 0 && targetPos == 1) || (sourcePos == 2 && targetPos == 3)) { // MY HAND -> MY WASTE
            result = source.readTopCard().map(c -> target.putCard(c)).orElse(false);
        } else if (targetPos == 1 || targetPos == 3) { // OTHER -> WASTE
            result = source.readTopCard().map(c -> target.enemyPutCard(c)).orElse(false);
        } else { // OTHER -> OTHER
            result = source.readTopCard().map(c -> target.putCard(c)).orElse(false);
        }

        if (result) {
            source.takeTopCard();
        }
        // TODO : can we move this entire if outside of Move? @J
        if ((sourcePos == 0 || sourcePos == 2) && source.getSize() == 0) {
            if (checkEmptyHand(gameTable, sourcePos)) {
                Service.getInstance().getClient().endGame(true, "winning condition");
            } else {
                Service.getInstance().getClient().swapHandWaste(sourcePos, sourcePos + 1);
            }
            ;
        }
        return result;
    }


    public void redo(GameTable gameTable) {
        target.putCard(source.takeTopCard().get());
    }

    public void undo(GameTable gameTable) {
        source.putCard(target.takeTopCard().get());
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

    private boolean checkEmptyHand(GameTable gameTable, int sourcePos) {
        Waste waste = gameTable.getPlayersCard().stream()
                .filter(pD -> pD.getHand().getPosition() == sourcePos)
                .map(pD -> pD.getWaste()).findFirst().get();

        if (gameTable.getPlayersCard().stream()
                .filter(pD -> pD.getHand().getPosition() == sourcePos)
                .allMatch(pD -> pD.getWaste().getSize() == 0)) {
            return true;
        } else {
            gameTable.swapPiles(source, waste);
            return false;
        }
    }

    private boolean noObligatoryMoveExists(GameTable gameTable, ICardSet source) {
        List<Foundation> foundations = gameTable.getFoundations().stream().map(cs -> (Foundation) cs).collect(Collectors.toList());
        if (source.getPosition() == 0 || source.getPosition() == 2) {
            return source.readTopCard()
                    .map(card -> foundations.stream().noneMatch(f -> f.tryPutCard(card))) //true if every tryPutCard returns false
                    .orElse(true); // true if hand is empty
        }
        return gameTable.getHouses().stream()
                .map(ICardSet::readTopCard)
                .filter(card -> card.isPresent())
                .noneMatch(card -> foundations.stream()
                        .anyMatch(f -> f.tryPutCard(card.get()))); //false if there is obligatory move possible
    }
}

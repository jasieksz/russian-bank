package pl.edu.agh.to2.russianBank.game.command;

import com.google.common.base.MoreObjects;
import pl.edu.agh.to2.russianBank.game.Card;
import pl.edu.agh.to2.russianBank.game.GameTable;
import pl.edu.agh.to2.russianBank.game.ICardSet;
import pl.edu.agh.to2.russianBank.ui.controllers.Service;

import java.util.Objects;
import java.util.Optional;

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
        if ((sourcePos == 0 && targetPos == 1) || (sourcePos == 2 && targetPos == 3)) { // MY HAND -> MY WASTE
            result = source.readTopCard().map(c -> target.putCard(c)).orElse(false);
        } else if (targetPos == 1 || targetPos == 3) { // OTHER -> WASTE
            result = source.readTopCard().map(c -> target.enemyPutCard(c)).orElse(false);
        } else { // OTHER -> OTHER
            result = source.readTopCard().map(c -> target.putCard(c)).orElse(false);
        }

        if (result){
            source.takeTopCard();
            Service.getInstance().getClient().move(this); //TODO : test if this is correct, if not move to MoveController
        }

        if ((sourcePos == 0 || sourcePos == 2) && source.getSize() == 0){
            int wastePos = gameTable.getPlayers().stream()
                    .filter(playerDeck -> playerDeck.getHand().getPosition() == sourcePos)
                    .map(playerDeck -> playerDeck.getWaste().getPosition()).findFirst().get();
            if (gameTable.getPlayers().stream()
                    .filter(playerDeck -> playerDeck.getHand().getPosition() == sourcePos)
                    .allMatch(playerDeck -> playerDeck.getWaste().getSize() == 0)){
                // TODO : send message YOU WON!!!!
            } else {
                Service.getInstance().getClient().swapHandWaste(sourcePos, wastePos);
                // TODO : swap locally
            }

        }

        return result;

//        if ((sourcePos == 0 && targetPos == 1) || (sourcePos == 2 && targetPos == 3)) {
//            // MY HAND -> MY WASTE
//            result.ifPresent(card -> target.putCard(card));
//        }
//        else if ((sourcePos == 0 && targetPos == 3) || (sourcePos == 2 && targetPos == 1)  // MY HAND -> ENEMY WASTE
//                || (sourcePos == 1 && targetPos == 3) || (sourcePos == 3 && targetPos == 1)) { // MY WASTE -> ENEMY WASTE
//
//            result.ifPresent(card -> target.enemyPutCard(card));
//        }
//        else if ((sourcePos > 3 && sourcePos < 20) && (targetPos == 1 || targetPos == 3)){
//            // HOUSE -> WASTE (assuming it's always enemy waste)
//        } else {
//            // put correct with target
//            // HOUSE -> HOUSE , WASTE -> HOUSE, HOUSE -> FOUNDATION
//            result.ifPresent(card -> target.putCard(card));
//        }
    }

    public void redo(GameTable gameTable) {
        target.putCard(source.takeTopCard().get()); // TODO : isPresent() missing warning
    }

    public void undo(GameTable gameTable) {
        source.putCard(target.takeTopCard().get()); // TODO : isPresent() missing warning
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
}

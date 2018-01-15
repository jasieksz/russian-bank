package pl.edu.agh.to2.russianBank.net.client;


import pl.edu.agh.to2.russianBank.game.GameState;
import pl.edu.agh.to2.russianBank.game.ICardSet;
import pl.edu.agh.to2.russianBank.game.Player;
import pl.edu.agh.to2.russianBank.game.command.Move;
import pl.edu.agh.to2.russianBank.game.command.MoveController;

/**
 * Callback interface for incoming messages.
 */
public interface ClientCallbacks {
    void startGame(GameState gameState, MoveController moveController, boolean missaStart);

    void movingCard(Player player, ICardSet previousSlot, ICardSet newSlot);

    void endGame(boolean won, String cause);

    void move(Move move);

    void swap(int handPosition, int wastePosition);

    void startTurn();

    /**
     * General network error occurred, probably fatal.
     *
     * @param ex exception describing error details.
     */
    void networkError(Throwable ex);
}

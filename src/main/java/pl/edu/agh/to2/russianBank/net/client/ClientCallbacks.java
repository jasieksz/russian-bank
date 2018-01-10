package pl.edu.agh.to2.russianBank.net.client;


import pl.edu.agh.to2.russianBank.game.Game;
import pl.edu.agh.to2.russianBank.game.GameTable;
import pl.edu.agh.to2.russianBank.game.ICardSet;
import pl.edu.agh.to2.russianBank.game.Player;
import pl.edu.agh.to2.russianBank.game.command.Move;

public interface ClientCallbacks {
    void startGame(Game game);

    void movingCard(Player player, ICardSet previousSlot, ICardSet newSlot);

    void endGame(boolean won, String cause);

    void move(Move move);

    void networkError(Throwable ex);
}

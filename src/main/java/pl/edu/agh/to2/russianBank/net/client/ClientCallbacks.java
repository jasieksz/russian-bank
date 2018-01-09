package pl.edu.agh.to2.russianBank.net.client;


import pl.edu.agh.to2.russianBank.game.GameTable;
import pl.edu.agh.to2.russianBank.game.ICardSet;
import pl.edu.agh.to2.russianBank.game.Player;
import pl.edu.agh.to2.russianBank.game.command.Move;

public interface ClientCallbacks {
   /* void startGame(GameTable table);*/

    void movingCard(Player player, ICardSet previousSlot, ICardSet newSlot);

    void endGame(String message);

    void move(Move move);
}

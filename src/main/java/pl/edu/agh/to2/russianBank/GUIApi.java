package pl.edu.agh.to2.russianBank;


import pl.edu.agh.to2.russianBank.game.GameTable;
import pl.edu.agh.to2.russianBank.game.ICardSet;
import pl.edu.agh.to2.russianBank.game.Player;

public interface GUIApi {
   /* void startGame(GameTable table);*/

    void movingCard(Player player, ICardSet previousSlot, ICardSet newSlot);

    void endGame(String message);
}

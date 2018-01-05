package pl.edu.agh.to2.russianBank.ui;

import org.junit.Test;
import pl.edu.agh.to2.russianBank.game.CardRank;
import pl.edu.agh.to2.russianBank.game.CardSuit;
import pl.edu.agh.to2.russianBank.ui.controllers.GameController;

import static org.junit.Assert.assertEquals;

public class GameControllerTest {

    @Test
    public void testBuildPictureName() {
        GameController controller = new GameController();
        assertEquals(controller.buildPictureName(CardRank.KING, CardSuit.HEARTS),"S_K");

        assertEquals(controller.buildPictureName(CardRank.ACE, CardSuit.CLUBS),"T_AS");
        assertEquals(controller.buildPictureName(CardRank.CARD_3, CardSuit.SPADES),"W_3");
        assertEquals(controller.buildPictureName(CardRank.JACK, CardSuit.DIAMONDS),"K_J");
        assertEquals(controller.buildPictureName(CardRank.QUEEN, CardSuit.DIAMONDS),"K_Q");
    }


}

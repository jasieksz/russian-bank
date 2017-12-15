package pl.edu.agh.to2.russianBank.game;
import pl.edu.agh.to2.russianBank.game.Card;
import pl.edu.agh.to2.russianBank.game.CardRank;
import pl.edu.agh.to2.russianBank.game.CardSuit;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertTrue;

public class CardTest {

    @Test
    public void oppositeSuitTest(){
        Card card = new Card(CardSuit.HEARTS, CardRank.ACE);
        List<CardSuit> expected = new ArrayList<>();
        expected.add(CardSuit.CLUBS);
        expected.add(CardSuit.SPADES);
        assertTrue(expected.equals(card.getOppositeSuits()));
    }
}

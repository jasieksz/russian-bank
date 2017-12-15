package test.java.pl.edu.agh.to2.russianBank.game;
import main.java.pl.edu.agh.to2.russianBank.game.Card;
import main.java.pl.edu.agh.to2.russianBank.game.CardRank;
import main.java.pl.edu.agh.to2.russianBank.game.CardSuit;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

// TODO : unable to run anything until we fix kotlin gradle!
public class CardTest {

    @Test
    public void oppositeSuitTest(){
        Card card = new Card(CardSuit.HEARTS, CardRank.ACE);
        List<CardSuit> expected = new ArrayList<>();
        expected.add(CardSuit.CLUBS);
        expected.add(CardSuit.SPADES);
        assertEquals(expected, card.getOppositeSuits());

    }
}

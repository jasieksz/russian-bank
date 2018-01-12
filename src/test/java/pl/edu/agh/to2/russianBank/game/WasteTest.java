package pl.edu.agh.to2.russianBank.game;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;


public class WasteTest {
    private Card card1 = new Card(CardSuit.HEARTS, CardRank.ACE);
    private Card card2 = new Card(CardSuit.HEARTS, CardRank.CARD_2);
    private Card card3 = new Card(CardSuit.HEARTS, CardRank.KING);
    private Card card4 = new Card(CardSuit.SPADES, CardRank.QUEEN);

    @Test
    public void takeTopCardTest(){
        Waste waste = new Waste();
        waste.putCard(card1);
        waste.putCard(card2);
        assertTrue(waste.takeTopCard().equals(Optional.of(card2)));
    }

    @Test
    public void readEmptyTopCardTest() {
        Waste waste = new Waste();
        assertTrue(waste.readTopCard().equals(Optional.empty()));
    }

    @Test
    public void putCardTest() {
        Waste waste = new Waste();
        waste.putCard(card1);
        assertTrue(waste.putCard(card4));
    }

    @Test
    public void enemyPutCardTest() {
        Waste waste = new Waste();
        waste.putCard(card1);
        assertTrue(!waste.enemyPutCard(card4));
    }
}

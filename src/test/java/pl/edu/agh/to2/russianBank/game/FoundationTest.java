package pl.edu.agh.to2.russianBank.game;

import kotlin.jvm.Throws;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;

public class FoundationTest {

    private Card card1 = new Card(CardSuit.HEARTS, CardRank.ACE);
    private Card card2 = new Card(CardSuit.HEARTS, CardRank.CARD_2);
    private Card card3 = new Card(CardSuit.HEARTS, CardRank.KING);

//    @Test(UnsupportedOperationException)
//    public void takeTopCardTest(){
//        List<Card> cardList = new ArrayList<>();
//        cardList.add(card1);
//        cardList.add(card2);
//        Foundation foundation = new Foundation(cardList);
//        assert()
//    }

    @Test
    public void readEmptyTopCardTest(){
        List<Card> cardList = new ArrayList<>();
        Foundation foundation = new Foundation(cardList);
        assertTrue(foundation.readTopCard().equals(Optional.empty()));
    }

    @Test
    public void readTopCardTest(){
        List<Card> cardList = new ArrayList<>();
        cardList.add(card1);
        cardList.add(card2);
        Foundation foundation = new Foundation(cardList);
        assertTrue(foundation.readTopCard().get().equals(card2));
    }

    @Test
    public void putCardTest(){
        List<Card> cardList = new ArrayList<>();
        cardList.add(card1);
        Foundation foundation = new Foundation(cardList);
        assertTrue(foundation.putCard(card2));
    }

    @Test
    public void putCard2Test(){
        List<Card> cardList = new ArrayList<>();
        cardList.add(card1);
        Foundation foundation = new Foundation(cardList);
        assertTrue(!foundation.putCard(card3));
    }



}

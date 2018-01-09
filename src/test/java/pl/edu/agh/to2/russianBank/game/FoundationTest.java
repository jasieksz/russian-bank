package pl.edu.agh.to2.russianBank.game;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;

public class FoundationTest {

    @Test
    public void takeTopCardTest(){
        Card card1 = new Card(CardSuit.HEARTS, CardRank.ACE);
        Card card2 = new Card(CardSuit.HEARTS, CardRank.CARD_2);
        List<Card> cardList = new ArrayList<>();
        cardList.add(card1);
        cardList.add(card2);
        Foundation foundation = new Foundation(cardList);
        assertTrue(foundation.takeTopCard().equals(Optional.empty()));
    }

    @Test
    public void lookUpTopCardTest(){
        List<Card> cardList = new ArrayList<>();
        Foundation foundation = new Foundation(cardList);
        assertTrue(foundation.readTopCard().equals(Optional.empty()));
    }

//    @Test
//    public void tryPutCardEmptyTest(){
//        List<Card> cardList = new ArrayList<>();
//        Foundation foundation = new Foundation(cardList);
//        Card card1 = new Card(CardSuit.HEARTS, CardRank.ACE);
//        assertTrue(foundation.);
//    }

}

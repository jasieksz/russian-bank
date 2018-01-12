package pl.edu.agh.to2.russianBank.game;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;

public class HandTest {
    private Card card1 = new Card(CardSuit.HEARTS, CardRank.ACE);
    private Card card2 = new Card(CardSuit.HEARTS, CardRank.CARD_2);
    private Card card3 = new Card(CardSuit.HEARTS, CardRank.KING);
    private Card card4 = new Card(CardSuit.SPADES, CardRank.QUEEN);

    @Test
    public void takeTopCardTest(){
        List<Card> cardList = new ArrayList<>();
        cardList.add(card1);
        cardList.add(card2);
        Hand hand = new Hand(cardList);
        assertTrue(hand.takeTopCard().equals(Optional.of(card2)));
    }

    @Test
    public void readEmptyTopCardTest(){
        List<Card> cardList = new ArrayList<>();
        Hand hand = new Hand(cardList);
        assertTrue(hand.readTopCard().equals(Optional.empty()));
    }

    @Test
    public void putCardTest(){
        List<Card> cardList = new ArrayList<>();
        cardList.add(card3);
        Hand hand = new Hand(cardList);
        assertTrue(!hand.putCard(card4));
    }

}

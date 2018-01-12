package pl.edu.agh.to2.russianBank.game;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;

public class HouseTest {

    private Card card1 = new Card(CardSuit.HEARTS, CardRank.ACE);
    private Card card2 = new Card(CardSuit.HEARTS, CardRank.CARD_2);
    private Card card3 = new Card(CardSuit.HEARTS, CardRank.KING);

    @Test
    public void takeTopCardTest(){
        List<Card> cardList = new ArrayList<>();
        cardList.add(card1);
        cardList.add(card2);
        House house = new House(cardList);
        assertTrue(house.takeTopCard().equals(Optional.of(card2)));
    }


}

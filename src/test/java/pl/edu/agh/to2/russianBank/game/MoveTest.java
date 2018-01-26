package pl.edu.agh.to2.russianBank.game;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.edu.agh.to2.russianBank.game.command.Move;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;

public class MoveTest {

    private static Card card1 = new Card(CardSuit.HEARTS, CardRank.ACE);
    private static Card card2 = new Card(CardSuit.HEARTS, CardRank.CARD_2);
    private static Card card3 = new Card(CardSuit.HEARTS, CardRank.KING);
    private static Card card4 = new Card(CardSuit.SPADES, CardRank.QUEEN);
    private static List<Card> cardList = new ArrayList<>();
    private static List<Card> cardList2 = new ArrayList<>();
    private GameTable gameTable = new GameTable();


    @BeforeClass
    public static void insertCardList(){
        cardList.add(card1);
        cardList.add(card2);
        cardList.add(card3);
        cardList.add(card4);
    }

    // TODO : Move has changed , fix it!
//    @Test
//    public void executeHandWaste(){
//        Hand hand = new Hand(cardList);
//        hand.setPosition(0);
//        Waste waste = new Waste();
//        waste.setPosition(1);
//        Move move = new Move(hand, waste);
//        boolean res = move.execute(gameTable);
//        assertTrue(res);
//
//    }
}

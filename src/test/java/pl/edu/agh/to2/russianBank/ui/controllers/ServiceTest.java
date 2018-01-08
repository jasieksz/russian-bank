package pl.edu.agh.to2.russianBank.ui.controllers;

import org.junit.Test;
import pl.edu.agh.to2.russianBank.game.CardRank;
import pl.edu.agh.to2.russianBank.game.CardSuit;

import static org.junit.Assert.*;

public class ServiceTest {

    @Test
    public void buildPictureName() {
        Service Service = new Service();
        assertEquals(Service.buildPictureName(CardRank.KING, CardSuit.HEARTS),"S_K");

        assertEquals(Service.buildPictureName(CardRank.ACE, CardSuit.CLUBS),"T_AS");
        assertEquals(Service.buildPictureName(CardRank.CARD_3, CardSuit.SPADES),"W_3");
        assertEquals(Service.buildPictureName(CardRank.JACK, CardSuit.DIAMONDS),"K_J");
        assertEquals(Service.buildPictureName(CardRank.QUEEN, CardSuit.DIAMONDS),"K_Q");
    }
}

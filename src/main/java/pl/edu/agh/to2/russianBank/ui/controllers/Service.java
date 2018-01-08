package pl.edu.agh.to2.russianBank.ui.controllers;

import javafx.scene.image.Image;
import pl.edu.agh.to2.russianBank.RussianBank;
import pl.edu.agh.to2.russianBank.game.Card;
import pl.edu.agh.to2.russianBank.game.CardRank;
import pl.edu.agh.to2.russianBank.game.CardSuit;

import java.io.File;

public class Service {

    public Image getImageForCard(Card card) {
        String picture = buildPictureName(card.getRank(), card.getSuit());
        return createImage("karty/" + picture + ".png");
    }

    public Image getWhiteImage() {
        return createImage("karty/White.png");
    }

    public String buildPictureName(CardRank r, CardSuit s) {
        return s.getRank() + "_" + r.getRankName();
    }

    public Image createImage(String pathname) {
        return new Image(RussianBank.class.getResourceAsStream(pathname));
    }
}

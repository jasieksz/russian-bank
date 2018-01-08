package pl.edu.agh.to2.russianBank.ui.controllers;

import javafx.scene.image.Image;
import pl.edu.agh.to2.russianBank.game.Card;
import pl.edu.agh.to2.russianBank.game.CardRank;
import pl.edu.agh.to2.russianBank.game.CardSuit;

import java.io.File;

public class Service {

    public Image getImageForCard(Card card) {
        String picture = buildPictureName(card.getRank(), card.getSuit());
        return createImage("resources/Karty/" + picture + ".png");
    }

    public Image getWhiteImage() {
        return createImage("resources/Karty/White.png");
    }

    public String buildPictureName(CardRank r, CardSuit s) {
        String result = s.getRank() + "_" + r.getRankName();
        System.out.println(result);
        return result;
    }

    public Image createImage(String pathname) {
        File file6 = new File(pathname);
        return new Image(file6.toURI().toString());
    }
}

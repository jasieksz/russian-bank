package pl.edu.agh.to2.russianBank.ui.controllers;

import javafx.scene.image.Image;
import pl.edu.agh.to2.russianBank.RussianBank;
import pl.edu.agh.to2.russianBank.game.Card;
import pl.edu.agh.to2.russianBank.game.CardRank;
import pl.edu.agh.to2.russianBank.game.CardSuit;
import pl.edu.agh.to2.russianBank.net.client.Client;


public class Service {
    private Client client;
    private Boolean isStackTaken = false;

    public void setStackTaken(Boolean stackTaken) {
        isStackTaken = stackTaken;
    }

    private static Service instance;
    private Service() {}

    public static Service getInstance() {
        if (instance == null) {
            instance = new Service();
        }
        return instance;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

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

    public void markCurrentPlayer(GameController controller, boolean myTurn) {
        String marked = "-fx-background-color: #e6c34d; -fx-text-fill: #311c09;";
        String unmarked = "-fx-background-color: none; -fx-text-fill: white;";
        if(myTurn) {
            controller.myName.setStyle(marked);
            controller.opponentName.setStyle(unmarked);
        }
        else {
            controller.myName.setStyle(unmarked);
            controller.opponentName.setStyle(marked);
        }

    }

}

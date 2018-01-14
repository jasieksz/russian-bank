package pl.edu.agh.to2.russianBank.ui.controllers;

import javafx.beans.InvalidationListener;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.edu.agh.to2.russianBank.RussianBank;
import pl.edu.agh.to2.russianBank.game.Card;
import pl.edu.agh.to2.russianBank.game.CardRank;
import pl.edu.agh.to2.russianBank.game.CardSuit;
import pl.edu.agh.to2.russianBank.net.client.Client;

import java.io.File;
import java.util.Observable;

public class Service {
    private Client client;
    private static final Logger LOG = LogManager.getLogger();
    //private BooleanPro myTurn = new SimpleBooleanProperty(false);
    private boolean myTurn = false;// =


    private Boolean isStackTaken = false;

    public void setStackTaken(Boolean stackTaken) {
        isStackTaken = stackTaken;
    }

    public Boolean getStackTaken() {
        return isStackTaken;
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

    public boolean getMyTurn() {
        return myTurn;
    }

    public void setMyTurn(boolean myTurn) {
        this.myTurn = myTurn;
    }

    public void changeTurn(GameController controller) {
        if(myTurn) {
            controller.myName.setStyle("-fx-background-color: #e6c34d; -fx-text-fill: #311c09;");
            controller.opponentName.setStyle("-fx-background-color: none; -fx-text-fill: white;");
        }
        else {
            controller.myName.setStyle("-fx-background-color: none; -fx-text-fill: white;");
            controller.opponentName.setStyle("-fx-background-color: #e6c34d; -fx-text-fill: #311c09;");
        }
    }


}

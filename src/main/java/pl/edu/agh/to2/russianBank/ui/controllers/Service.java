package pl.edu.agh.to2.russianBank.ui.controllers;

import javafx.scene.image.Image;
import pl.edu.agh.to2.russianBank.RussianBank;
import pl.edu.agh.to2.russianBank.game.Card;
import pl.edu.agh.to2.russianBank.game.CardRank;
import pl.edu.agh.to2.russianBank.game.CardSetPosition;
import pl.edu.agh.to2.russianBank.game.CardSuit;
import pl.edu.agh.to2.russianBank.net.client.Client;


public class Service {
    private Client client;
    private boolean myTurn = true;
    private boolean handIsSource;
    private boolean missaStart = false;
    public int myHandIndex = CardSetPosition.HAND_1.getPosition();
    public int opponentHandIndex = CardSetPosition.HAND_2.getPosition();
    public int myWasteIndex = CardSetPosition.WASTE_1.getPosition();
    public int opponentWasteIndex = CardSetPosition.WASTE_2.getPosition();


    public void setHandIsSource(boolean handIsSource) {
        this.handIsSource = handIsSource;
    }

    public boolean isHandIsSource() {
        return handIsSource;
    }

    public boolean isMyTurn() {
        return myTurn;
    }

    private Service() {}

    private final static class ServiceHolder {
        private final static Service instance = new Service();
    }
    public final static Service getInstance() {
        return ServiceHolder.instance;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setMyTurn(boolean turn) {
        this.myTurn = turn;
    }

    public Client getClient() {
        return client;
    }

    public void setMissaStart(boolean missaStart) {
        this.missaStart = missaStart;
        if(missaStart) { 
            myHandIndex = 2;  
            opponentHandIndex = 0; 
            myWasteIndex = 3;
            opponentWasteIndex = 1;
        }
    }

    public boolean isMissaStart() {
        return missaStart;
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

    public void markCurrentPlayer(GameController controller) {
        String marked = "-fx-background-color: #3333ff; -fx-text-fill: white;";
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

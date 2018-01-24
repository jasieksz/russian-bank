package pl.edu.agh.to2.russianBank.ui.controllers;

import javafx.scene.image.ImageView;
import pl.edu.agh.to2.russianBank.game.*;

import java.util.*;

public class ListenersAdder {

    private Service service = Service.getInstance();
    private Map<Integer, CardView> foundations;
    private Map<Integer, CardView> hands;
    private Map<Integer, CardView> wastes;
    private Map<Integer, List<CardView>> houses;
    org.apache.logging.log4j.Logger LOG = org.apache.logging.log4j.LogManager.getLogger();

    ListenersAdder(Map<Integer, CardView> foundations, Map<Integer, CardView> hands, Map<Integer, CardView> wastes,
                   Map<Integer, List<CardView>> houses) {
        this.foundations = foundations;
        this.hands = hands;
        this.wastes = wastes;
        this.houses = houses;
    }
    /**
     * Function adds listeners to each Card from each pile. Each listener waits for changes and when they occur
     * it checks all lists in view and sets proper image in position which changes.
     *
     * @param table it is state of table received from server
     */

    public void addListChangeListeners(GameTable table, int myPlayerNumberInTable) {
        for (int i = 0; i < table.getHouses().size(); i++) {
            ICardSet house = table.getHouses().get(i);
            final int index = i;
            house.addListener(c -> refreshHouse(index, house));
            refreshHouse(index, house);
        }

        for (int i = 0; i < table.getFoundations().size(); i++) {
            ICardSet foundation = table.getFoundations().get(i);
            final int index = i;

            foundation.addListener(c -> {
                Optional<Card> card = foundation.readTopCard();
                ImageView imageView = foundations.get(index);
                imageView.setImage(card.map(e -> service.getImageForCard(e)).orElse(service.getWhiteImage()));
            });
        }

        for (int i = 0; i < table.getPlayersCard().size(); i++) {
            addListenersForPlayer(i, table, myPlayerNumberInTable);
        }

    }

    public void refreshHouse(int index, ICardSet house) {
        List<Card> card = house.getCards();
        for (int j = 0; j < houses.get(index).size(); j++) {
            if (j < card.size()) {
                houses.get(index).get(j).setImage(service.getImageForCard(card.get(j)));
            } else {
                houses.get(index).get(j).setImage(null);
            }
        }

        if (card.isEmpty()) {
            houses.get(index).get(0).setImage(service.getWhiteImage());
        }
        LOG.debug(houses.get(index));

    }

    /**
     * Function to add listeners for hand and waste for chosen player.
     */
    public void addListenersForPlayer(int playerId, GameTable table, int myPlayerNumberInTable) {
        System.out.println(playerId);
        int index;
        if(playerId == myPlayerNumberInTable) {index = 0;}
        else index = 1;

        Hand hand = table.getPlayersCard().get(playerId).getHand();
        Waste waste = table.getPlayersCard().get(playerId).getWaste();

        waste.addListener(c -> {
            Optional<Card> card = waste.readTopCard();
            ImageView imageView = wastes.get(index);
            imageView.setImage(card.map(e -> service.getImageForCard(e)).orElse(service.getWhiteImage()));
        });
        hand.addListener(c -> {
            Optional<Card> card = hand.readTopCard();
            ImageView imageView = hands.get(index);
            imageView.setImage(card.map(e -> service.getImageForCard(e)).orElse(service.getWhiteImage()));
        });
    }

}

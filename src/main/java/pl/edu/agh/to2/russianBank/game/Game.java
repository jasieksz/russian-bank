package pl.edu.agh.to2.russianBank.game;

import javafx.collections.FXCollections;
import pl.edu.agh.to2.russianBank.game.command.MoveController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Game {
    private Player playerOne;
    private Player playerTwo;
    private GameTable gameTable;
    private MoveController moveController;

    public Game(Player playerA, Player playerB) {
        this.playerOne = playerA;
        this.playerTwo = playerB;
        this.gameTable = new GameTable(new ArrayList<ICardSet>(createPiles()));
        this.moveController = new MoveController(this.getGameTable());
    }

    public GameTable getGameTable() {
        return gameTable;
    }

    public MoveController getMoveController() {
        return moveController;
    }

    private List<ICardSet> createPiles() {
        List<ICardSet> piles = new ArrayList<>();
        Hand p1Hand = playerOne.getPlayerDeck().getHand();
        Waste p1Waste = playerOne.getPlayerDeck().getWaste();
        Hand p2Hand = playerTwo.getPlayerDeck().getHand();
        Waste p2Waste = playerTwo.getPlayerDeck().getWaste();

        p1Hand.setPosition(0);
        p1Waste.setPosition(1);
        p2Hand.setPosition(2);
        p2Waste.setPosition(3);
        piles.add(p1Hand);
        piles.add(p1Waste);
        piles.add(p2Hand);
        piles.add(p2Waste);

        for (int i = CardSetPosition.HOUSE_1.getPosition(); i <= CardSetPosition.HOUSE_8.getPosition(); i++) {
            House newHouse = new House(FXCollections.observableArrayList(), i);
            Optional<Card> startCard =  (i < CardSetPosition.HOUSE_1.getPosition() + 4) ?
                    p1Hand.takeTopCard() :
                    p2Hand.takeTopCard();
            startCard.map(newHouse::putCard);
            piles.add(newHouse);
        }
        for (int i = CardSetPosition.FOUNDATION_1.getPosition(); i <= CardSetPosition.FOUNDATION_8.getPosition(); i++) {
            piles.add(new Foundation(FXCollections.observableArrayList(), i));
        }

        return piles;
    }
}


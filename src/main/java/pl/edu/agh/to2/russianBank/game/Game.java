package pl.edu.agh.to2.russianBank.game;

import javafx.collections.FXCollections;
import pl.edu.agh.to2.russianBank.game.command.MoveController;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Player> players;
    private GameTable gameTable;
    private MoveController moveController;

    public Game(List<Player> players) {
        this.players = players;
        this.gameTable = new GameTable(players, new ArrayList<ICardSet>(createPiles()));
        this.moveController = new MoveController(this.getGameTable());
    }

    public List<Player> getPlayers() {
        return players;
    }

    public GameTable getGameTable() {
        return gameTable;
    }

    public MoveController getMoveController() {
        return moveController;
    }

    private List<ICardSet> createPiles() {
        List<ICardSet> piles = new ArrayList<>();
        players.get(0).getPlayerDeck().getHand().setPosition(0);
        players.get(0).getPlayerDeck().getWaste().setPosition(1);
        players.get(1).getPlayerDeck().getHand().setPosition(2);
        players.get(1).getPlayerDeck().getWaste().setPosition(3);
        piles.add(players.get(0).getPlayerDeck().getHand());
        piles.add(players.get(0).getPlayerDeck().getWaste());
        piles.add(players.get(1).getPlayerDeck().getHand());
        piles.add(players.get(1).getPlayerDeck().getWaste());

        for (int i = 4; i < 12; i++) {
            House newHouse = new House(FXCollections.observableArrayList(), i);
            Card startCard = players.get(i%2).getPlayerDeck().getHand().takeTopCard().get();
            newHouse.putCard(startCard);
            piles.add(newHouse);
        }
        for (int i = 12; i < 20; i++) {
            piles.add(new Foundation(FXCollections.observableArrayList(), i));
        }
        return piles;
    }
}


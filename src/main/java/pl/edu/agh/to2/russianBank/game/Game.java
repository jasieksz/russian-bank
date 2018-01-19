package pl.edu.agh.to2.russianBank.game;

import javafx.collections.FXCollections;
import pl.edu.agh.to2.russianBank.game.command.MoveController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {
    // TODO : unpack players
    private List<Player> players;
    private GameTable gameTable;
    private MoveController moveController;

    public Game(List<Player> players) {
        this.players = players;
        this.gameTable = new GameTable(players, new ArrayList<ICardSet>(createPiles()));
        this.moveController = new MoveController(this.getGameTable());
    }

    public Game(Player playerA, Player playerB) {
        this(Arrays.asList(playerA, playerB));
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
        // TODO : change positions to ENUM
        players.get(0).getPlayerDeck().getHand().setPosition(0);
        players.get(0).getPlayerDeck().getWaste().setPosition(1);
        players.get(1).getPlayerDeck().getHand().setPosition(2);
        players.get(1).getPlayerDeck().getWaste().setPosition(3);
        piles.add(players.get(0).getPlayerDeck().getHand());
        piles.add(players.get(0).getPlayerDeck().getWaste());
        piles.add(players.get(1).getPlayerDeck().getHand());
        piles.add(players.get(1).getPlayerDeck().getWaste());

        for (int i = CardSetPosition.HOUSE_1.getPosition(); i <= CardSetPosition.HOUSE_8.getPosition(); i++) {
            House newHouse = new House(FXCollections.observableArrayList(), i);
            Card startCard = players.get(i%2).getPlayerDeck().getHand().takeTopCard().get();
            newHouse.putCard(startCard);
            piles.add(newHouse);
        }
        for (int i = CardSetPosition.FOUNDATION_1.getPosition(); i <= CardSetPosition.FOUNDATION_8.getPosition(); i++) {
            piles.add(new Foundation(FXCollections.observableArrayList(), i));
        }
        return piles;
    }
}


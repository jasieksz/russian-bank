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
        startGame();
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

        // TODO : move 4 top cards from each Hand into Houses
        for (int i = 4; i < 12; i++) {
            piles.add(new House(FXCollections.observableArrayList(), i, moveController));
        }
        for (int i = 12; i < 20; i++) {
            piles.add(new Foundation(FXCollections.observableArrayList(), i, moveController));
        }
        return piles;
    }


    public void startGame() {
        for (int i = 4; i < 12; i++) {
//            Move move = new Move(players.get(0).getPlayerDeck().getHand().getPosition(), i);
//            moveController.executeCommand(move);
        }


        return;
    }
}


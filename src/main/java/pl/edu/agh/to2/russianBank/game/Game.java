package pl.edu.agh.to2.russianBank.game;

import javafx.collections.FXCollections;
import pl.edu.agh.to2.russianBank.game.command.MoveController;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Player> players;
    private GameTable gameTable;
    private MoveController moveController;

    // TODO : change constructor, it should create gameTable and moveController itself
    public Game(List<Player> players) {
        this.players = players;

        this.gameTable = new GameTable(players, new ArrayList<ICardSet>(createPiles()));

        this.moveController = new MoveController(this.getGameTable());
        //GameTable gameTable, MoveController moveController
        //this.gameTable = gameTable;
        //this.moveController = moveController;
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

    private List<ICardSet> createPiles(){


        List<ICardSet> piles = new ArrayList<>();
        for (int i = 0; i<8; i++){
            piles.add(new House(FXCollections.observableArrayList(), i));
        }
        for (int i = 8; i<16; i++){
            piles.add(new Foundation(FXCollections.observableArrayList(), i));
        }
        return piles;
    }


    public void startGame(){

        // TODO : shuffle cards, setup houses, etc. ???

        List<PlayerDeck> playerDecks = new ArrayList<>();
        List<House> houses = new ArrayList<>();
        List<Foundation> foundations = new ArrayList<>();
        playerDecks.add(new PlayerDeck(new Hand(new ArrayList<>()), new Waste()));
        playerDecks.add(new PlayerDeck(new Hand(new ArrayList<>()), new Waste()));

        houses.add(new House(new ArrayList<>()));
        houses.add(new House(new ArrayList<>()));
        houses.add(new House(new ArrayList<>()));
        houses.add(new House(new ArrayList<>()));
        houses.add(new House(new ArrayList<>()));
        houses.add(new House(new ArrayList<>()));
        houses.add(new House(new ArrayList<>()));
        houses.add(new House(new ArrayList<>()));

        foundations.add(new Foundation(new ArrayList<>()));
        foundations.add(new Foundation(new ArrayList<>()));
        foundations.add(new Foundation(new ArrayList<>()));
        foundations.add(new Foundation(new ArrayList<>()));
        foundations.add(new Foundation(new ArrayList<>()));
        foundations.add(new Foundation(new ArrayList<>()));
        foundations.add(new Foundation(new ArrayList<>()));
        foundations.add(new Foundation(new ArrayList<>()));


        return;
    }
}


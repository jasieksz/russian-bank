package pl.edu.agh.to2.russianBank.game;

import pl.edu.agh.to2.russianBank.game.command.MoveController;

import java.util.List;

public class Game {
    private List<Player> players;
    private GameTable gameTable;
    private MoveController moveController;

    public Game(List<Player> players, GameTable gameTable, MoveController moveController) {
        this.players = players;
        this.gameTable = gameTable;
        this.moveController = moveController;
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

    public void startGame(){
        // TODO : shuffle cards, setup houses, etc. ???

        for (Player player : players) {

        }

        return;
    }
}


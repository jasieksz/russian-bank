package pl.edu.agh.to2.russianBank.ui.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.edu.agh.to2.russianBank.Constants;
import pl.edu.agh.to2.russianBank.game.*;
import pl.edu.agh.to2.russianBank.net.client.Client;
import pl.edu.agh.to2.russianBank.ui.ClientCallbacksImpl;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static java.lang.Thread.sleep;

public class StartGameController implements Initializable {
    private static final Logger LOG = LogManager.getLogger();

    @FXML
    public Button okButton;
    @FXML
    public TextField nameField;
    public javafx.scene.control.Button deleteButton;
    public GameTable table;
    @FXML
    public Label statusLbl;

    private GameController controller;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void handleOkAction(ActionEvent actionEvent) {
        String playerName = nameField.getText();

        okButton.setDisable(true);
        statusLbl.setText("Connecting to game server...");

        final ClientCallbacksImpl callbacks = new ClientCallbacksImpl((Stage) okButton.getScene().getWindow());

        try {
            LOG.info("Connecting to game server");
            Client.connect(Constants.SERVER_URI, callbacks)
                    .thenComposeAsync(client -> {
                        LOG.info("Connected, sending hello");
                        Service.getInstance().setClient(client);        //ok?
                        return client.hello(playerName);
                    })
                    .thenRunAsync(() -> {
                        Platform.runLater(() -> {
                            statusLbl.setText("Waiting for other player...");

                           /* try {
                                sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            GameState gameState = createGameState(playerName);
                            callbacks.startGame(gameState);*/
                        });
                    })
                    .exceptionally(e -> {
                        LOG.error("Error connecting to client", e);
                        onConnectError();
                        return null;
                    });
        } catch (Exception e) {
            LOG.error("Error creating client instance", e);
            onConnectError();
        }
    }

    public void onConnectError() {
        Platform.runLater(() -> {
            statusLbl.setText("Failed to connect to game server!");
            okButton.setDisable(false);
        });
    }

    //----------- function to test GUI ------------

    private GameState createGameState(String myName) {
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

        List<Player> players = new ArrayList<>();
        players.add(new Player(myName));
        players.add(new Player("second player"));
        return new GameState(players, new GameTable(playerDecks, houses, foundations));
    }
}

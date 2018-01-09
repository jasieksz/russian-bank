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
import pl.edu.agh.to2.russianBank.game.GameTable;
import pl.edu.agh.to2.russianBank.net.client.Client;
import pl.edu.agh.to2.russianBank.ui.ClientCallbacksImpl;

import java.net.URL;
import java.util.ResourceBundle;

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
                        Platform.runLater(() -> {
                            statusLbl.setText("Waiting for other player...");
                        });
                        return client.hello(playerName);
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
            statusLbl.setText("Failed to to connect to game server!");
            okButton.setDisable(false);
        });
    }
}

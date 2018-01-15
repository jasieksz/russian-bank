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
import pl.edu.agh.to2.russianBank.game.Game;
import pl.edu.agh.to2.russianBank.game.GameState;
import pl.edu.agh.to2.russianBank.game.GameTable;
import pl.edu.agh.to2.russianBank.game.Player;
import pl.edu.agh.to2.russianBank.game.command.MoveController;
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
    @FXML
    public Label statusLbl;

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
                        Service.getInstance().setClient(client);
                        return client.hello(playerName);
                    })
                    .thenRunAsync(() -> {
                        Platform.runLater(() -> {
                            statusLbl.setText("Waiting for other player...");
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
}

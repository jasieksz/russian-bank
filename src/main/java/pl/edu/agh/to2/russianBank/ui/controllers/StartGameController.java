package pl.edu.agh.to2.russianBank.ui.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

        try {
            LOG.info("Connecting to game server");
            Client.connect(Constants.SERVER_URI, new ClientCallbacksImpl())
                    .thenApplyAsync(client -> {
                        LOG.info("Connected, sending hello");
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

//        //czekamy na serwer aż da nam gametable, moze dać też Game cały i znak, że zaczynamy grę
//
//        try {
//            FXMLLoader loader = new FXMLLoader();
//            Stage oldStage = (Stage) okButton.getScene().getWindow();
//            oldStage.close();
//            Parent root = loader.load(RootLayout.class.getResource("Game.fxml"));
//            Stage stage = new Stage();
//
//            stage.setTitle("Garibaldka");
//            stage.getIcons().add(new Image(RussianBank.class.getResourceAsStream("image.png")));
//            Scene scene = new Scene(root, 1200, 1200);
//
//            stage.setScene(scene);
//
//
//            stage.setMaximized(true);
//            stage.show();
//
//            //controller = loader.getController();
//            //controller.setTable(table);
//
//        } catch (IOException e) {
//            LOG.error("Error creating game stage", e);
//        }
    }

    public void onConnectError() {
        Platform.runLater(() -> {
            statusLbl.setText("Failed to to connect to game server!");
            okButton.setDisable(false);
        });
    }
}

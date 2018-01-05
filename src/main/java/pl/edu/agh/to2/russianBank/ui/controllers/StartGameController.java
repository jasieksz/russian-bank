package pl.edu.agh.to2.russianBank.ui.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.edu.agh.to2.russianBank.GUIApi;
import pl.edu.agh.to2.russianBank.RussianBank;
import pl.edu.agh.to2.russianBank.game.*;
import pl.edu.agh.to2.russianBank.net.client.Client;
import pl.edu.agh.to2.russianBank.ui.views.RootLayout;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class StartGameController implements Initializable {

    private static final Logger LOG = LogManager.getLogger();
    @FXML
    public Button okButton;
    @FXML
    public TextField nameField;
    public javafx.scene.control.Button deleteButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void handleOkAction(ActionEvent actionEvent) {

        //wywołanie metody z serwera
        String s = nameField.getText();
        System.out.println(s);
        //RussianBank.setName(s);

        try {
            Stage oldStage = (Stage) okButton.getScene().getWindow();
            oldStage.close();
            Parent root = FXMLLoader.load(RootLayout.class.getResource("Game.fxml"));
            Stage stage = new Stage();
            //stage.setMaximized(true);
            //stage.setFullScreen(true);

            stage.setTitle("Garibaldka");
            Scene scene = new Scene(root, 1200, 1200);



            scene.widthProperty().addListener(new ChangeListener<Number>() {

                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    //GameController.updateWidthConstaints(newValue.doubleValue());
                }
            });

            stage.setScene(scene);


            stage.setMaximized(true);
            stage.show();
        /*    GUIApi gui = new GUIApi() {
                @Override
                public void startGame(GameTable table) {

                }

                @Override
                public void movingCard(Player player, ICardSet previousSlot, ICardSet newSlot) {

                }

                @Override
                public void endGame(String message) {
                    LOG.info("end game: {}", message);
                }
            };


            //try (Client client = Client.connect(new URI("ws://localhost:8666/game"));, gui).get()) {
//               client.hello(s).get();
              //  client.awaitClose(10, TimeUnit.SECONDS);
            } catch (Exception e) {
                LOG.error(e);
            }
            //GameController.updateWidthConstaints(scene.getWidth());
*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
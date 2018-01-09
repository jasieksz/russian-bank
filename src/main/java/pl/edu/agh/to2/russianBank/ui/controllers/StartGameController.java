package pl.edu.agh.to2.russianBank.ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.edu.agh.to2.russianBank.RussianBank;
import pl.edu.agh.to2.russianBank.game.GameTable;
import pl.edu.agh.to2.russianBank.ui.views.RootLayout;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartGameController implements Initializable {
    private static final Logger LOG = LogManager.getLogger();


    public String name;
    @FXML
    public Button okButton;
    @FXML
    public TextField nameField;
    public javafx.scene.control.Button deleteButton;
    public GameTable table;

    private GameController controller;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void handleOkAction(ActionEvent actionEvent) {

        //wywołanie metody z serwera
        String s = nameField.getText();
        LOG.debug(s);
        name = s;
        //RussianBank.setName(s);

        //czekamy na serwer aż da nam gametable, moze dać też Game cały i znak, że zaczynamy grę

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(RootLayout.class.getResource("Game.fxml"));
            loader.load();
            Stage oldStage = (Stage) okButton.getScene().getWindow();
            oldStage.close();

            GameController controller = loader.getController();
            controller.setName(s);

            //Parent root = loader.load(RootLayout.class.getResource("Game.fxml"));
            Parent root = loader.getRoot();

            Stage stage = new Stage();

            stage.setTitle("Garibaldka");
            stage.getIcons().add(new Image(RussianBank.class.getResourceAsStream("image.png")));
            Scene scene = new Scene(root, 1200, 1200);

            stage.setScene(scene);

            stage.setMaximized(true);

            //controller = loader.getController();
            //System.out.print(controller);
            //controller.setName(s);
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
            LOG.error(e);
        }
    }
}

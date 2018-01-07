package pl.edu.agh.to2.russianBank.ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.edu.agh.to2.russianBank.game.GameTable;
import pl.edu.agh.to2.russianBank.ui.views.RootLayout;

import java.io.IOException;
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

    private GameController controller;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void handleOkAction(ActionEvent actionEvent) {

        //wywołanie metody z serwera
        String s = nameField.getText();
        System.out.println(s);
        //RussianBank.setName(s);

        try {
            FXMLLoader loader = new FXMLLoader();
            Stage oldStage = (Stage) okButton.getScene().getWindow();
            oldStage.close();
            Parent root = loader.load(RootLayout.class.getResource("Game.fxml"));
            Stage stage = new Stage();

            stage.setTitle("Garibaldka");
            Scene scene = new Scene(root, 1200, 1200);

            stage.setScene(scene);


            stage.setMaximized(true);
            stage.show();

            controller = loader.getController();
            controller.setTable(table);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
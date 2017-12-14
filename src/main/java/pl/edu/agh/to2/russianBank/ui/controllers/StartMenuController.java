package pl.edu.agh.to2.russianBank.ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.FXML;

import javafx.stage.Stage;
import pl.edu.agh.to2.russianBank.ui.views.RootLayout;

/*import java.awt.*;
import java.awt.Button;
import java.awt.TextField;
import java.awt.event.ActionEvent;
*/
import java.io.IOException;

public class StartMenuController {

    @FXML
    public TextField nameField;


    @FXML
    public Button okButton;
    public javafx.scene.control.Button deleteButton;

    @FXML
    public void handleOkAction(ActionEvent actionEvent) {


        String s = nameField.getText();
        System.out.println(s);
        try {
            Stage oldStage = (Stage) okButton.getScene().getWindow();
            oldStage.close();
            Parent root = FXMLLoader.load(RootLayout.class.getResource("views/StartGame.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Garibaldka");
            stage.setScene(new Scene(root, 800, 600));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

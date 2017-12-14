package pl.edu.agh.to2.russianBank.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartGameController implements Initializable {


    @FXML
    public Button startButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    public void changeCard(MouseEvent mouseEvent) throws IOException {

    }

    public void handleStartAction(ActionEvent actionEvent) {

        try {
            Stage oldStage = (Stage) startButton.getScene().getWindow();
            oldStage.close();
            Parent root = FXMLLoader.load(RootLayout.class.getResource("Game.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Garibaldka");
            stage.setScene(new Scene(root, 800, 600));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

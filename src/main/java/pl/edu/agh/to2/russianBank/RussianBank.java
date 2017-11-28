package pl.edu.agh.to2.russianBank;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.edu.agh.to2.russianBank.ui.RootLayout;

import java.io.IOException;

public class RussianBank extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(RootLayout.class.getResource("RootLayout.fxml"));
            primaryStage.setTitle("Barigaldka");
            primaryStage.setScene(new Scene(root, 300, 275));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

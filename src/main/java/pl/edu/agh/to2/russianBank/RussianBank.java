package pl.edu.agh.to2.russianBank;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.edu.agh.to2.russianBank.net.server.Server;
import pl.edu.agh.to2.russianBank.ui.views.RootLayout;

import java.io.IOException;
import java.util.Arrays;

public class RussianBank extends Application {
    private static final Logger LOG = LogManager.getLogger();

    public static void main(String[] args) {
        if (Arrays.asList(args).contains("-server")) {
            Server.main(args);
        } else {
            launch(args);
        }
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(RootLayout.class.getResource("StartGame.fxml"));

            Scene scene = new Scene(root, 800, 600);

            primaryStage.setTitle("Garibaldka");
            Image image = new Image(RussianBank.class.getResourceAsStream("image.png"));
            primaryStage.getIcons().add(image);
            primaryStage.setScene(scene);
            primaryStage.setMaximized(true);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

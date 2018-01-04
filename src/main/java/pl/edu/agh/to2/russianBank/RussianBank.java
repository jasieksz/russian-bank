package pl.edu.agh.to2.russianBank;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.edu.agh.to2.russianBank.net.client.Client;
import pl.edu.agh.to2.russianBank.net.server.Server;
import pl.edu.agh.to2.russianBank.net.transport.HelloMessage;
import pl.edu.agh.to2.russianBank.ui.views.RootLayout;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class RussianBank extends Application {
    private static final Logger LOG = LogManager.getLogger();

    public static void main(String[] args) {
        if (Arrays.asList(args).contains("-server")) {
            Server.main(args);
        } else {
//            try(Client client = Client.connect("ws://localhost:8666/game").get()) {
//                client.sendMessage(new HelloMessage("ziomek")).get();
//            } catch (Exception e) {
//                LOG.error(e);
//            }
            launch(args);
        }
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root =
                    //FXMLLoader.load(RootLayout.class.getResource("RootLayout.fxml"));
                    FXMLLoader.load(RootLayout.class.getResource("StartGame.fxml"));

            Scene scene = new Scene(root, 1000, 600);



            primaryStage.setTitle("Garibaldka");
            File file = new File("resources/image.png");
            Image image = new Image(file.toURI().toString());
            primaryStage.getIcons().add(image);
            //("@../../../../../../../../../../resources/image.png"));
            primaryStage.setScene(scene);
            primaryStage.setMaximized(true);
            primaryStage.centerOnScreen();

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package pl.edu.agh.to2.russianBank;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.edu.agh.to2.russianBank.net.client.RussianBankClient;
import pl.edu.agh.to2.russianBank.net.server.RussianBankServer;
import pl.edu.agh.to2.russianBank.net.transport.HelloMessage;
import pl.edu.agh.to2.russianBank.net.transport.Message;
import pl.edu.agh.to2.russianBank.ui.RootLayout;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class RussianBank extends Application {
    private static final Logger LOG = LogManager.getLogger();

    public static void main(String[] args) {
        if (Arrays.asList(args).contains("-server")) {
            RussianBankServer.main(args);
        } else {
            try(RussianBankClient client = RussianBankClient.connect("ws://localhost:8666/game").get()) {
                client.sendMessage(new HelloMessage("ziomek")).get();
            } catch (Exception e) {
                LOG.error(e);
            }

//            launch(args);
        }
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

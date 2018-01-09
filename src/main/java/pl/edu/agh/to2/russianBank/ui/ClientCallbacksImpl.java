package pl.edu.agh.to2.russianBank.ui;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.edu.agh.to2.russianBank.RussianBank;
import pl.edu.agh.to2.russianBank.game.GameTable;
import pl.edu.agh.to2.russianBank.game.ICardSet;
import pl.edu.agh.to2.russianBank.game.Player;
import pl.edu.agh.to2.russianBank.game.command.Move;
import pl.edu.agh.to2.russianBank.net.client.ClientCallbacks;
import pl.edu.agh.to2.russianBank.ui.controllers.RootLayout;

import java.io.IOException;

public class ClientCallbacksImpl implements ClientCallbacks {
    private static final Logger LOG = LogManager.getLogger();

    private Stage startGameStage;

    public ClientCallbacksImpl(Stage startGameStage) {
        this.startGameStage = startGameStage;
    }

    @Override
    public void startGame(GameTable table) {
        // FIXME: RACE CONDITIONS!!!
        Platform.runLater(() -> {
            try {
                startGameStage.close();
                startGameStage = null;

                Parent root = FXMLLoader.load(RootLayout.class.getResource("Game.fxml"));
                Stage stage = new Stage();

                stage.setTitle("Garibaldka");
                stage.getIcons().add(new Image(RussianBank.class.getResourceAsStream("image.png")));
                Scene scene = new Scene(root, 1200, 1200);

                stage.setScene(scene);


                stage.setMaximized(true);
                stage.show();

                //controller = loader.getController();
                //controller.setTable(table);

            } catch (IOException e) {
                LOG.error("Error creating game stage", e);
            }
        });
    }

    @Override
    public void movingCard(Player player, ICardSet previousSlot, ICardSet newSlot) {
    }

    @Override
    public void endGame(String message) {

    }

    @Override
    public void move(Move move) {

    }

    @Override
    public void networkError(Throwable ex) {
        // TODO:
        ex.printStackTrace();
    }


}

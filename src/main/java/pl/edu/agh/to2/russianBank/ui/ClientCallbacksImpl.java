package pl.edu.agh.to2.russianBank.ui;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.edu.agh.to2.russianBank.RussianBank;
import pl.edu.agh.to2.russianBank.game.*;
import pl.edu.agh.to2.russianBank.game.command.Move;
import pl.edu.agh.to2.russianBank.game.command.MoveController;
import pl.edu.agh.to2.russianBank.net.client.ClientCallbacks;
import pl.edu.agh.to2.russianBank.ui.controllers.GameController;
import pl.edu.agh.to2.russianBank.ui.controllers.RootLayout;

import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class ClientCallbacksImpl implements ClientCallbacks {
    private static final Logger LOG = LogManager.getLogger();

    private Stage startGameStage;
    private Stage gameStage;
    private GameController controller;

    public ClientCallbacksImpl(Stage startGameStage) {
        this.startGameStage = startGameStage;
    }

    @Override
    public void startGame(GameState gameState) {

        Platform.runLater(() -> {
            try {

                startGameStage.close();
                startGameStage = null;

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(pl.edu.agh.to2.russianBank.ui.views.RootLayout.class.getResource("Game.fxml"));
                loader.load();
                Parent root = loader.getRoot();

                Stage stage = new Stage();
                gameStage = stage;
                stage.setTitle("Garibaldka");
                stage.getIcons().add(new Image(RussianBank.class.getResourceAsStream("image.png")));
                Scene scene = new Scene(root, 1200, 1200);

                stage.setScene(scene);
                stage.setMaximized(true);
                stage.show();

                controller = loader.getController();
                controller.setTable(gameState.getGameTable());
                controller.setName(gameState.getPlayers());

                /* to set GUI
                sleep(2000);

                GameTable table = controller.getTable();
                move(new Move(table.getHouses().get(7),table.getHouses().get(5)));*/

            } catch (IOException e) {
                LOG.error("Error creating game stage", e);
            } /*catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        });
    }

    @Override
    public void movingCard(Player player, ICardSet previousSlot, ICardSet newSlot) {
        //what we should do here??? how to change model? change model and as a result
        // listeners will change view??? how we can change model (GameTable/Game) here?
        //controller.setTable(...); ?
    }

    @Override
    public void endGame(boolean won, String cause) {

        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Game ended");
        a.setHeaderText("Game ended");
        a.setResizable(true);
        String content;
        if(won) {
            content = "YOU WON!!! \n Congratulations";
        }
        else {
            content = "YOU LOST!!! \n Try again";
        }
        content = content.concat("\n"+cause);
        a.setContentText(content);
        a.showAndWait();

        gameStage.close();
        System.exit(0);
    }

    @Override
    public void move(Move move) {
        move.execute(controller.getTable());
    }

    @Override
    public void networkError(Throwable ex) {

        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setTitle("Network error");
        a.setHeaderText("Network error");
        a.setResizable(true);
        String content="There is problem with network \n try later...";
        a.setContentText(content);
        a.showAndWait();

        gameStage.close();
        System.exit(0);
        ex.printStackTrace();
    }

}

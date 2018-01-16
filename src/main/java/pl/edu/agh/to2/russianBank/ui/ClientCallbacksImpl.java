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
import pl.edu.agh.to2.russianBank.ui.controllers.Service;

import java.io.IOException;

public class ClientCallbacksImpl implements ClientCallbacks {
    private static final Logger LOG = LogManager.getLogger();

    private Stage startGameStage;
    private Stage gameStage;
    private GameController controller;

    public ClientCallbacksImpl(Stage startGameStage) {
        this.startGameStage = startGameStage;
    }

    @Override
    public void startGame(GameState gameState, MoveController moveController, boolean missaStart) {

        LOG.info("missaStart value:"+ missaStart);
        if(missaStart) Service.getInstance().setMissaStart(true);

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
                Scene scene = new Scene(root, 1200, 800);

                stage.setScene(scene);
                //stage.setMaximized(true);
                stage.setOnCloseRequest(event -> {
                        LOG.info("Stage is closing");
                        System.exit(0);
                });
                stage.show();

                controller = loader.getController();
                controller.setMoveController(moveController);
                controller.setTable(gameState.getGameTable());
                controller.setName(gameState.getPlayers());

            } catch (IOException e) {
                LOG.error("Error creating game stage", e);
            }
        });
    }

    @Override
    public void movingCard(Player player, ICardSet previousSlot, ICardSet newSlot) {
      //in next release, if we want to add button
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

    @Override
    public void swap(int handPosition, int wastePosition) {
        Hand hand = controller.getTable().getPlayersCard().stream()
                .map(pD -> pD.getHand())
                .filter(h -> h.getPosition() == handPosition).findFirst().get();
        Waste waste = controller.getTable().getPlayersCard().stream()
                .map(pD -> pD.getWaste())
                .filter(h -> h.getPosition() == wastePosition).findFirst().get();

        controller.getTable().swapPiles(hand, waste);
    }

    @Override
    public void startTurn() {
        Service.getInstance().setMyTurn(true);
        Service.getInstance().markCurrentPlayer(controller);
    }

}

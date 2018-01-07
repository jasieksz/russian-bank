package pl.edu.agh.to2.russianBank.ui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.edu.agh.to2.russianBank.GUIApi;
import pl.edu.agh.to2.russianBank.game.GameTable;
import pl.edu.agh.to2.russianBank.game.ICardSet;
import pl.edu.agh.to2.russianBank.game.Player;
import pl.edu.agh.to2.russianBank.ui.controllers.GameController;
import pl.edu.agh.to2.russianBank.ui.views.RootLayout;

import java.io.IOException;

public class GUI implements GUIApi {

    private GameController controller;

    /*@Override
    public void startGame(GameTable table) {

        try {
            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.load(RootLayout.class.getResource("Game.fxml"));
            Stage stage = new Stage();

            stage.setTitle("Garibaldka");
            Scene scene = new Scene(root, 1200, 1200);

            scene.widthProperty().addListener(new ChangeListener<Number>() {

                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    //GameController.updateWidthConstaints(newValue.doubleValue());
                }
            });

            stage.setScene(scene);
            stage.setMaximized(true);
            stage.show();
            controller = loader.getController();
            controller.setTable(table);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/

    @Override
    public void movingCard(Player player, ICardSet previousSlot, ICardSet newSlot) {
    }

    @Override
    public void endGame(String message) {

    }


}

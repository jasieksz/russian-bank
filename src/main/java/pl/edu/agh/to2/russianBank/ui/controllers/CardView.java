package pl.edu.agh.to2.russianBank.ui.controllers;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.edu.agh.to2.russianBank.game.ICardSet;
import pl.edu.agh.to2.russianBank.game.command.MoveController;

/**
 * Class made to enable drag & drop operation on cards
 */


public class CardView extends ImageView {

    private static final Logger LOG = LogManager.getLogger();
    private ICardSet cardSet;


    public CardView(Image image, ICardSet cardSet, MoveController moveController, GameController controller) {
        super(image);
        this.cardSet = cardSet;

        setOnDragDetected(event -> {

            if (Service.getInstance().isMyTurn()) {
                if (!(cardSet.getPosition() == 2)) {
                    Dragboard dragboard = startDragAndDrop(TransferMode.ANY);
                    ClipboardContent content = new ClipboardContent();
                    ImageView imageView = new ImageView(getImage());
                    imageView.setFitHeight(100);
                    imageView.setFitWidth(65);
                    content.putImage(imageView.snapshot(null, null));
                    dragboard.setContent(content);
                    event.consume();
                } else {
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setTitle("Wrong move");
                    a.setHeaderText("Wrong move");
                    a.setResizable(true);
                    String content = "What are you doing? These are not your cards!";
                    a.setContentText(content);
                    a.showAndWait();
                }
            }
        });

        setOnDragOver(e -> e.acceptTransferModes(TransferMode.ANY));

        setOnDragDropped(event -> {

            if (event.getGestureSource() instanceof CardView && event.getGestureTarget() instanceof CardView) {
                CardView sourceCardView = (CardView) event.getGestureSource();
                CardView targetCardView = (CardView) event.getGestureTarget();

                boolean successful = targetCardView.cardSet.makeMove(sourceCardView.cardSet, moveController);

                if(!successful) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setTitle("Wrong move");
                    a.setHeaderText("Wrong move");
                    a.setResizable(true);
                    String content = "This move is incorrect";
                    a.setContentText(content);
                    a.showAndWait();
                }

                if(cardSet.getPosition() ==1 || !successful) {
                    LOG.info("Turn ended");
                    LOG.info(successful);
                    /*Service.getInstance().setMyTurn(false);
                    Service.getInstance().markCurrentPlayer(controller);
                    Service.getInstance().getClient().endTurn();
                    sourceCardView.setImage(Service.getInstance().createImage("karty/Gora1.png"));*/
                }
            }
            event.consume();
        });
    }
}

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
    private boolean handIsSource = false;

    /**
     * Constructor, sets events on drag & drop
     * @param image for calling a superclass constructor
     * @param cardSet ICardSet from model binding with this field
     * @param moveController instance of moveController from GameController class
     * @param controller instance of game stage controller
     */

    public CardView(Image image, ICardSet cardSet, MoveController moveController, GameController controller) {
        super(image);
        this.cardSet = cardSet;

        setOnDragDetected(event -> {

            if (Service.getInstance().isMyTurn()) {
                if(cardSet.getPosition() == 0) {
                    handIsSource = true;
                    System.out.println("pozycja"+cardSet.getPosition());
                }
                if (!(cardSet.getPosition() == 2)) {
                    Dragboard dragboard = startDragAndDrop(TransferMode.ANY);
                    ClipboardContent content = new ClipboardContent();
                    ImageView imageView = new ImageView(getImage());
                    imageView.setFitHeight(100);
                    imageView.setFitWidth(65);
                    content.putImage(imageView.snapshot(null, null));
                    dragboard.setContent(content);
                    event.consume();
                } else
                    displayAlert("What are you doing? These are not your cards!");
            }
        });

        setOnDragOver(e -> e.acceptTransferModes(TransferMode.ANY));

        setOnDragDropped(event -> {

            if (event.getGestureSource() instanceof CardView && event.getGestureTarget() instanceof CardView) {
                CardView sourceCardView = (CardView) event.getGestureSource();
                CardView targetCardView = (CardView) event.getGestureTarget();

                boolean successful = targetCardView.cardSet.makeMove(sourceCardView.cardSet, moveController);

                if(!successful)
                    displayAlert("This move is incorrect");

                if(cardSet.getPosition() == 1 || !successful)
                    endTurn(sourceCardView,controller);
                //handIsSource = false;
            }
            event.consume();
        });
    }


    private void displayAlert(String content) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Wrong move");
        a.setHeaderText("Wrong move");
        a.setResizable(true);
        a.setContentText(content);
        a.showAndWait();
    }

    private void endTurn(CardView sourceCardView, GameController controller) {
        LOG.info("Turn ended");
        /*Service.getInstance().setMyTurn(false);
        Service.getInstance().markCurrentPlayer(controller);
        Service.getInstance().getClient().endTurn();*/
        if(handIsSource)
            sourceCardView.setImage(Service.getInstance().createImage("karty/Gora1.png"));
    }

}

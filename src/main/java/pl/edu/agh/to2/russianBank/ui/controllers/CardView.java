package pl.edu.agh.to2.russianBank.ui.controllers;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.edu.agh.to2.russianBank.game.ICardSet;
import pl.edu.agh.to2.russianBank.game.command.Move;
import pl.edu.agh.to2.russianBank.game.command.MoveCodes;
import pl.edu.agh.to2.russianBank.game.command.MoveController;

/**
 * Class made to enable drag & drop operation on cards
 */


public class CardView extends ImageView {

    private static final Logger LOG = LogManager.getLogger();
    private ICardSet cardSet;
    private int miniatureHight = 100;
    private int miniatureWidth = 65;

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
                setHandIsSource();
                showMove(event);
            }
        });

        setOnDragOver(e -> e.acceptTransferModes(TransferMode.ANY));

        setOnDragDropped(event -> {

            if (event.getGestureSource() instanceof CardView && event.getGestureTarget() instanceof CardView) {

                CardView sourceCardView = (CardView) event.getGestureSource();
                CardView targetCardView = (CardView) event.getGestureTarget();

                int successful = targetCardView.cardSet.makeMove(sourceCardView.cardSet, moveController);
                sendMove(successful, sourceCardView);
                tryEndTurn(successful, sourceCardView, controller);
            }
            event.consume();
        });
    }

    private void setHandIsSource() {
        if(cardSet.getPosition() == Service.getInstance().myHandIndex)
        {
            Service.getInstance().setHandIsSource(true);
            System.out.println("Hand was source");
        }
        else {
            Service.getInstance().setHandIsSource(false);
        }
    }

    private void showMove(MouseEvent event) {
        if ((cardSet.getPosition() == Service.getInstance().opponentHandIndex)) {
            displayAlert("What are you doing? These are not your cards!");
        }
        else {
            Dragboard dragboard = startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            ImageView imageView = new ImageView(getImage());
            imageView.setFitHeight(miniatureHight);
            imageView.setFitWidth(miniatureWidth);
            content.putImage(imageView.snapshot(null, null));
            dragboard.setContent(content);
            event.consume();
        }
    }

    private void sendMove(int successful, CardView sourceCardView) {
        if(successful == MoveCodes.ACC.getCode()){
            Service.getInstance().getClient()
                    .move(new Move(sourceCardView.cardSet.getPosition(), this.cardSet.getPosition()));
        }
        else if (successful == MoveCodes.REJ.getCode()){
            displayAlert("This move is incorrect");
        }
        else if (successful == MoveCodes.SWAP.getCode()){
            Service.getInstance().getClient()
                    .swapHandWaste(sourceCardView.cardSet.getPosition(),sourceCardView.cardSet.getPosition() + 1);

        }
        else if(successful == MoveCodes.WIN.getCode()) {
            Service.getInstance().getClient().endGame(true, "You won");
        }
    }

    private void tryEndTurn(int successful, CardView sourceCardView, GameController controller) {
        if(cardSet.getPosition() == Service.getInstance().myWasteIndex || successful == MoveCodes.REJ.getCode() ||
                successful == MoveCodes.SWAP.getCode()) {
            endTurn(sourceCardView,controller);
        }
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
        Service.getInstance().setMyTurn(false);
        Service.getInstance().markCurrentPlayer(controller);
        Service.getInstance().getClient().endTurn();
        if(Service.getInstance().isHandIsSource())
            sourceCardView.setImage(Service.getInstance().createImage("karty/Gora1.png"));
    }

}

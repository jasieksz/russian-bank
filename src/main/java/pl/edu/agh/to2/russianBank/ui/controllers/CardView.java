package pl.edu.agh.to2.russianBank.ui.controllers;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import pl.edu.agh.to2.russianBank.game.ICardSet;
import pl.edu.agh.to2.russianBank.game.command.MoveController;

public class CardView extends ImageView {

    private ICardSet cardSet;

    public CardView(Image image, ICardSet cardSet, MoveController moveController) {
        super(image);
        this.cardSet = cardSet;

        setOnDragDetected(event -> {
            Dragboard dragboard = startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            ImageView imageView = new ImageView(getImage());
            imageView.setFitHeight(100);
            imageView.setFitWidth(65);
            content.putImage(imageView.snapshot(null, null));
            dragboard.setContent(content);
            event.consume();
        });

        setOnDragOver(e -> e.acceptTransferModes(TransferMode.ANY));

        setOnDragDropped(event -> {
            if (event.getGestureSource() instanceof CardView && event.getGestureTarget() instanceof CardView) {
                CardView sourceCardView = (CardView) event.getGestureSource();
                CardView targetCardView = (CardView) event.getGestureTarget();
                // TODO : makeMove returns now boolean, if false alert player about illegal move
                // also illegal move ==> end of player's turn @Game
                targetCardView.cardSet.makeMove(sourceCardView.cardSet, moveController);

                //if(!cardSet.makeMove(sourceCardView.cardSet))
                //wyświetlić alert
                /*Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Wrong move");
                a.setHeaderText("Wrong move");
                a.setResizable(true);
                String content = "this move is incorrect";
                a.setContentText(content);
                a.showAndWait();*/
            }
            event.consume();
        });
    }
}

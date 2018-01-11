package pl.edu.agh.to2.russianBank.ui.controllers;


import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import pl.edu.agh.to2.russianBank.game.Card;
import pl.edu.agh.to2.russianBank.game.ICardSet;

import java.util.Optional;

public class CardView extends ImageView {

    private ICardSet cardSet;

    public CardView(Image image, ICardSet cardSet) {
        super(image);
        this.cardSet = cardSet;

        setOnDragDetected(event -> {

            /*if(cardSet.getPosition()==0 && (Service.getInstance().getStackTaken()!=false) {
            }*/

            Dragboard dragboard = startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            ImageView imageView = new ImageView(getImage());
            imageView.setFitHeight(100);
            imageView.setFitWidth(50);
            content.putImage(imageView.snapshot(null, null));
            dragboard.setContent(content);
            event.consume();
        });

        setOnMouseEntered(event -> setCursor(Cursor.OPEN_HAND));

        setOnDragExited(event -> {
            if (event.getGestureSource() instanceof CardView) {
                if(cardSet.getPosition()==0){
                    this.setImage(Service.getInstance().createImage("karty/Gora1.png"));
                }
                CardView sourceCardView = (CardView) event.getGestureSource();
                // TODO : makeMove returns now boolean, if false alert player about illegal move
                // also illegal move ==> end of player's turn @Game
                cardSet.makeMove(sourceCardView.cardSet);

            }
            event.consume();
        });
    }
}

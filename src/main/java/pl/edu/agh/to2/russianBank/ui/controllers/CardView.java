package pl.edu.agh.to2.russianBank.ui.controllers;


import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import pl.edu.agh.to2.russianBank.game.ICardSet;

public class CardView extends ImageView {

    private ICardSet cardSet;

    public CardView(Image image, ICardSet cardSet) {
        super(image);
        this.cardSet = cardSet;

        setOnDragDetected(event -> {
            //if(cardSet.getPosition()==0) when hand
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
                CardView sourceCardView = (CardView) event.getGestureSource();
                cardSet.makeMove(sourceCardView.cardSet);
            }
            event.consume();
        });
    }
}

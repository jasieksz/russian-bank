package pl.edu.agh.to2.russianBank.ui.controllers;


import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;
import pl.edu.agh.to2.russianBank.game.Card;
import pl.edu.agh.to2.russianBank.game.ICardSet;

import java.util.Optional;

public class CardView extends ImageView {

    private ICardSet cardSet;

    public CardView(Image image, ICardSet cardSet) {
        super(image);
        this.cardSet = cardSet;

        setOnDragDetected(event -> {

            /*if(cardSet.getPosition()==0 && cardSet.readTopCard()==) {
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
        //    setOnDragEntered(event -> {
            if (event.getGestureSource() instanceof CardView) {
                if(cardSet.getPosition()==2){
                    this.setImage(Service.getInstance().createImage("karty/Gora1.png"));
                }
                CardView sourceCardView = (CardView) event.getGestureSource();
                cardSet.makeMove(sourceCardView.cardSet);

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

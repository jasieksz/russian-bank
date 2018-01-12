package pl.edu.agh.to2.russianBank.ui.controllers;


import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import pl.edu.agh.to2.russianBank.game.Card;
import pl.edu.agh.to2.russianBank.game.GameTable;
import pl.edu.agh.to2.russianBank.game.ICardSet;
import pl.edu.agh.to2.russianBank.game.command.MoveController;

import java.util.Optional;

public class CardView extends ImageView {

    private ICardSet cardSet;

    private static ICardSet target;

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

        addEventFilter(DragEvent.DRAG_ENTERED, e -> {
            target = cardSet;
        });

        addEventFilter(DragEvent.DRAG_DONE, event -> {
            if (event.getGestureSource() instanceof CardView) {
                ICardSet t = target;
                if (t != null) {
                    t.makeMove(cardSet, new MoveController(new GameTable())); // TODO : CHANGE THIS! @Game
                }
                CardView sourceCardView = (CardView) event.getGestureSource();
                // TODO : makeMove returns now boolean, if false alert player about illegal move
                // TODO : makeMove also needs moveController as 2nd arguemnt, moveController instance is in Game
                // if true Service.getInstance().getClient().move(new Move(source, target)); cos w tym stylu
                // if false koniec tury
                // if true & sourec=Hand & target=Waste ==> wysłanie move na serwer i koniec tury
                cardSet.makeMove(sourceCardView.cardSet, new MoveController(new GameTable()));

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

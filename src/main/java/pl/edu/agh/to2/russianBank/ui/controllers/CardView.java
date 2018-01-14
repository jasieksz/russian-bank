package pl.edu.agh.to2.russianBank.ui.controllers;




import com.sun.org.apache.xpath.internal.SourceTree;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.edu.agh.to2.russianBank.game.ICardSet;
import pl.edu.agh.to2.russianBank.game.command.MoveController;



import static org.eclipse.jetty.http.HttpParser.LOG;

public class CardView extends ImageView {

    private static final Logger LOG = LogManager.getLogger();
    private ICardSet cardSet;
    private boolean wasHand;
    private GameController controller;
    DragEvent event;
    //private SimpleBooleanProperty myTurn = new SimpleBooleanProperty(false);
    //private ObservableBooleanValue myTurn;// =

    public CardView(Image image, ICardSet cardSet, MoveController moveController, GameController c) {
        super(image);
        this.cardSet = cardSet;
        this.wasHand = false;
        controller = c;


        setOnDragDetected(event -> {
            if(!(cardSet.getPosition()==2)) {
                Dragboard dragboard = startDragAndDrop(TransferMode.ANY);
                ClipboardContent content = new ClipboardContent();
                ImageView imageView = new ImageView(getImage());
                imageView.setFitHeight(100);
                imageView.setFitWidth(65);
                content.putImage(imageView.snapshot(null, null));
                dragboard.setContent(content);
                if (cardSet.getPosition() == 0) this.wasHand = true;

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
        });

        setOnDragOver(e -> e.acceptTransferModes(TransferMode.ANY));

        setOnDragDropped(event -> {
            if (event.getGestureSource() instanceof CardView && event.getGestureTarget() instanceof CardView) {
                CardView sourceCardView = (CardView) event.getGestureSource();
                CardView targetCardView = (CardView) event.getGestureTarget();
                //--CHECK ME--
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
                    Service.getInstance().changeTurn(c);
                    controller.rootPane.setDisable(true);  //it does not work, we should block stage/scene here
                    //controller.rootPane.getParent().getScene().getWindow().getScene().setDisable(true);
                    //TODO: send message that turn ended
                    this.event.acceptTransferModes(TransferMode.NONE);
                    Service.getInstance().getClient().endTurn();
                }
            }
            event.consume();
        });
    }
}

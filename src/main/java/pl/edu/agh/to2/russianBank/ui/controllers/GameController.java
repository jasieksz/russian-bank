package pl.edu.agh.to2.russianBank.ui.controllers;

import com.google.common.collect.Lists;
import com.sun.org.apache.xpath.internal.SourceTree;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.edu.agh.to2.russianBank.game.*;
import pl.edu.agh.to2.russianBank.game.command.MoveController;

import java.awt.*;
import java.net.URL;
import java.util.*;
import java.util.List;

public class GameController implements Initializable {
    private static final Logger LOG = LogManager.getLogger();

    @FXML
    public GridPane gridPane;
    public RowConstraints row1;
    public ColumnConstraints col1;

    private GameTable table;

    private MoveController moveController;

    public GameTable getTable() {
        return table;
    }

    private Map<Integer, CardView> foundations = new HashMap<>();
    private Map<Integer, CardView> hands = new HashMap<>();
    private Map<Integer, CardView> wastes = new HashMap<>();
    private Map<Integer, List<CardView>> houses = new HashMap<>();

    private Service service = Service.getInstance();

    private int myPlayerNumberInTable;
    private int opponentPlayerNumberInTable;

    @FXML
    public Label myName;

    @FXML
    public Label opponentName;

    @FXML
    public Button endTurn;


    public void initialize() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        boolean missaStart = service.isMissaStart();
        myPlayerNumberInTable = missaStart ? 1 : 0;
        opponentPlayerNumberInTable = missaStart ? 0 : 1;
        
        service.setMyTurn(missaStart);
        service.markCurrentPlayer(this);
        initializeButtons();
    }

    /**
     * Function set players names in proper labels in view
     *
     * @param players list of players in this instance of game
     */

    public void setName(List<Player> players) {

        String name = players.get(myPlayerNumberInTable).getName();
        String opponentName = players.get(opponentPlayerNumberInTable).getName();
        this.myName.setText(name);
        this.myName.setAlignment(Pos.BOTTOM_CENTER);

        GridPane.setHalignment(this.myName, HPos.CENTER);
        GridPane.setValignment(this.myName, VPos.TOP);
        GridPane.setConstraints(this.myName, 0, 9, 2, 2);

        this.opponentName.setText(opponentName);
        this.opponentName.setAlignment(Pos.BOTTOM_CENTER);

        GridPane.setHalignment(this.opponentName, HPos.CENTER);
        GridPane.setValignment(this.opponentName, VPos.BOTTOM);
        GridPane.setConstraints(this.opponentName, 25, 3, 2, 2);
    }

    /**
     * Function set ImageViews in proper position in stage
     *
     * @param images list of CardView e.g. foundations, houses...
     */

    private void addImageViews(int row, int minColumn, List<CardView> images) {
        for (int i = 0; i < images.size(); i++) {
            GridPane.setConstraints(images.get(i), minColumn + i, row);
        }
    }

    /**
     * Function creates field of type CardView and set in proper position in gridPane.
     */

    private CardView createField(Image image, ICardSet cardSet) {
        CardView field = new CardView(image, cardSet, moveController, this);
        field.fitWidthProperty().bind(gridPane.widthProperty().multiply(col1.getPercentWidth()).divide(100));
        field.fitHeightProperty().bind(gridPane.heightProperty().multiply(row1.getPercentHeight()).divide(100));
        field.setPreserveRatio(true);
        return field;
    }

    @FXML
    public void uncoverCardFromStack() {
        LOG.debug("Stack uncovered!");

        if(service.isMyTurn()) {
            Optional<Card> card = table.getPlayersCard().get(myPlayerNumberInTable).getHand().readTopCard();
            card.ifPresent(c -> {
                hands.get(0).setImage(service.getImageForCard(c));
            });
        }
    }

    public void setMoveController(MoveController moveController) {
        this.moveController = moveController;
    }

    public void setTable(GameTable table) {
        this.table = table;
        initializeBoard();
        addListChangeListeners(table);
    }

    /**
     * Function rewrite from variable gameTable (taken after connecting with server)
     * to proper map (foundations, houses, hands, wastes).
     */
    private void initializeBoard() {
        Image image1 = service.createImage("karty/Gora1.png");
        Image image2 = service.getWhiteImage();
        Image image4 = service.createImage("karty/Gora2.png");

        for (int i = 0; i < 8; i++) {
            foundations.put(i, createField(image2, table.getFoundations().get(i)));
        }

        hands.put(0, createField(image1, table.getPlayersCard().get(myPlayerNumberInTable).getHand()));
        hands.get(0).setOnMouseClicked(event -> {
            uncoverCardFromStack();
        });

        hands.put(1, createField(image4, table.getPlayersCard().get(opponentPlayerNumberInTable).getHand()));

        wastes.put(0, createField(image2, table.getPlayersCard().get(myPlayerNumberInTable).getWaste()));
        wastes.put(1, createField(image2, table.getPlayersCard().get(opponentPlayerNumberInTable).getWaste()));

        for (int i = 0; i < 8; i++) {
            houses.put(i, new ArrayList<>());
            for (int j = 0; j < 9; j++) {
                houses.get(i).add(createField(null, table.getHouses().get(i)));
            }
            houses.get(i).get(0).setImage(service.getWhiteImage());
        }

        houses.values().stream().flatMap(Collection::stream).forEach(imageView -> {
            imageView.setPreserveRatio(true);
            gridPane.getChildren().add(imageView);
        });

        gridPane.getChildren().addAll(hands.values());
        gridPane.getChildren().addAll(wastes.values());
        gridPane.getChildren().addAll(foundations.values());

        GridPane.setConstraints(hands.get(0), 0, 11);
        GridPane.setConstraints(wastes.get(0), 1, 11);
        GridPane.setConstraints(foundations.get(0), 12, 3);
        GridPane.setConstraints(foundations.get(1), 12, 5);
        GridPane.setConstraints(foundations.get(2), 12, 7);
        GridPane.setConstraints(foundations.get(3), 12, 9);
        GridPane.setConstraints(foundations.get(4), 14, 3);
        GridPane.setConstraints(foundations.get(5), 14, 5);
        GridPane.setConstraints(foundations.get(6), 14, 7);
        GridPane.setConstraints(foundations.get(7), 14, 9);
        GridPane.setConstraints(wastes.get(1), 25, 1);
        GridPane.setConstraints(hands.get(1), 26, 1);

        addImageViews(3, 3, Lists.reverse(houses.get(0)));
        addImageViews(5, 3, Lists.reverse(houses.get(1)));
        addImageViews(7, 3, Lists.reverse(houses.get(2)));
        addImageViews(9, 3, Lists.reverse(houses.get(3)));

        addImageViews(3, 15, houses.get(4));
        addImageViews(5, 15, houses.get(5));
        addImageViews(7, 15, houses.get(6));
        addImageViews(9, 15, houses.get(7));
    }

    /**
     * Function adds listeners to each Card from each pile. Each listener waits for changes and when they occur
     * it checks all lists in view and sets proper image in position which changes.
     *
     * @param table it is state of table received from server
     */

    private void addListChangeListeners(GameTable table) {
        for (int i = 0; i < table.getHouses().size(); i++) {
            ICardSet house = table.getHouses().get(i);
            final int index = i;
            house.addListener(c -> refreshHouse(index, house));
            refreshHouse(index, house);
        }

        for (int i = 0; i < table.getFoundations().size(); i++) {
            ICardSet foundation = table.getFoundations().get(i);
            final int index = i;

            foundation.addListener(c -> {
                Optional<Card> card = foundation.readTopCard();
                ImageView imageView = foundations.get(index);
                imageView.setImage(card.map(e -> service.getImageForCard(e)).orElse(service.getWhiteImage()));
            });
        }

        for (int i = 0; i < table.getPlayersCard().size(); i++) {
            addListenersForPlayer(i);
        }

    }

    private void refreshHouse(int index, ICardSet house) {
        List<Card> card = house.getCards();
        for (int j = 0; j < houses.get(index).size(); j++) {
            if (j < card.size()) {
                houses.get(index).get(j).setImage(service.getImageForCard(card.get(j)));
            } else {
                houses.get(index).get(j).setImage(null);
            }
        }

        if (card.isEmpty()) {
            houses.get(index).get(0).setImage(service.getWhiteImage());
        }
    }

    /**
     * Function to add listeners for hand and waste for chosen player.
     */
    private void addListenersForPlayer(int playerId) {
        System.out.println(playerId);
        int index;
        if(playerId == myPlayerNumberInTable) {index = 0;}
        else index = 1;

        Hand hand = table.getPlayersCard().get(playerId).getHand();
        Waste waste = table.getPlayersCard().get(playerId).getWaste();

        waste.addListener(c -> {
            Optional<Card> card = waste.readTopCard();
            ImageView imageView = wastes.get(index);
            imageView.setImage(card.map(e -> service.getImageForCard(e)).orElse(service.getWhiteImage()));
        });
        hand.addListener(c -> {
            Optional<Card> card = hand.readTopCard();
            ImageView imageView = hands.get(index);
            imageView.setImage(card.map(e -> service.getImageForCard(e)).orElse(service.getWhiteImage()));
        });
    }

    public void handleClickAction() throws Exception {
        LOG.debug("End turn clicked!");
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("End turn");
        a.setHeaderText("End turn");
        a.setResizable(true);
        String content = "Try again...";
        a.setContentText(content);
        a.showAndWait();
        service.getClient().close();
        Stage stageToClose = (Stage) endTurn.getScene().getWindow();
        stageToClose.close();
        System.exit(0);
    }

    /**
     * To use in next release
     */

    private void initializeButtons() {
        Image image6 = service.createImage("karty/budzik.png");

        ImageView field1 = new ImageView(image6);
        field1.fitWidthProperty().bind(gridPane.widthProperty().multiply(col1.getPercentWidth()).divide(150));
        field1.fitHeightProperty().bind(gridPane.heightProperty().multiply(row1.getPercentHeight()).divide(120));

        final ToggleButton toggle = new ToggleButton();
        toggle.setGraphic(field1);
        toggle.setStyle("-fx-background-color: #00802b");
        field1.imageProperty().bind(Bindings
                .when(toggle.selectedProperty())
                .then(image6)
                .otherwise(image6)
        );

        HBox hbBtn = new HBox();
        hbBtn.setAlignment(Pos.CENTER);
        hbBtn.getChildren().add(toggle);
        toggle.setOnAction(event -> LOG.debug("Button clicked"));
        gridPane.add(hbBtn, 25, 11);

        GridPane.setConstraints(endTurn, 25, 9);
    }
}

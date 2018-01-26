package pl.edu.agh.to2.russianBank.ui.controllers;


import javafx.beans.binding.Bindings;
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

    public MoveController getMoveController() {
        return moveController;
    }

    private MoveController moveController;
    private int myPlayerNumberInTable;
    private int opponentPlayerNumberInTable;

    private Map<Integer, CardView> foundations = new HashMap<>();
    private Map<Integer, CardView> hands = new HashMap<>();
    private Map<Integer, CardView> wastes = new HashMap<>();
    private Map<Integer, List<CardView>> houses = new HashMap<>();

    private Service service = Service.getInstance();

    @FXML
    public Label myName;

    @FXML
    public Label opponentName;

    @FXML
    public Button endTurn;

    private StyleBuilder styleBuilder = new StyleBuilder();

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

    public GameTable getTable() {
        return table;
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

        styleBuilder.setLabelsPositions(this.myName, this.opponentName);
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

    //TODO how to do pretty code here?
    @FXML
    private void uncoverCardFromStack() {
        LOG.debug("Stack uncovered!");
        if(service.isMyTurn()) {
            table.getPlayersCards(myPlayerNumberInTable)
                    .get(0)
                    .readTopCard()
                    .ifPresent(card ->
                            hands.get(0)
                            .setImage(service.getImageForCard(card)));
        }
    }

    public void setMoveController(MoveController moveController) {
        this.moveController = moveController;
    }

    public void setTable(GameTable table) {
        this.table = table;

        initializeBoard();
        ListenersAdder listenersAdder = new ListenersAdder(foundations, hands, wastes, houses);
        listenersAdder.addListChangeListeners(table, myPlayerNumberInTable);
    }

    /**
     * Function rewrite from variable gameTable (taken after connecting with server)
     * to proper map (foundations, houses, hands, wastes).
     */
    private void initializeBoard() {
        addFieldsToMaps();
        addPilesToGridPane();
        StyleBuilder builder = new StyleBuilder();
        builder.setPosition(foundations, hands, wastes, houses);
    }

    private void addPilesToGridPane() {
        houses.values().stream().flatMap(Collection::stream).forEach(imageView -> {
            imageView.setPreserveRatio(true);
            gridPane.getChildren().add(imageView);
        });

        gridPane.getChildren().addAll(hands.values());
        gridPane.getChildren().addAll(wastes.values());
        gridPane.getChildren().addAll(foundations.values());

    }

    private void addFieldsToMaps() {
        Image image1 = service.createImage("karty/Gora1.png");
        Image image2 = service.getWhiteImage();
        Image image4 = service.createImage("karty/Gora2.png");

        for (int i = 0; i < 8; i++) {
            foundations.put(i, createField(image2, table.getFoundations().get(i)));
        }

        hands.put(0, createField(image1, table.getPlayersCards(myPlayerNumberInTable).get(0)));
        hands.get(0).setOnMouseClicked(event -> uncoverCardFromStack());
        hands.put(1, createField(image4, table.getPlayersCards(opponentPlayerNumberInTable).get(0)));

        wastes.put(0, createField(image2, table.getPlayersCards(myPlayerNumberInTable).get(1)));
        wastes.put(1, createField(image2, table.getPlayersCards(opponentPlayerNumberInTable).get(1)));

        for (int i = 0; i < 8; i++) {
            houses.put(i, new ArrayList<>());
            for (int j = 0; j < 9; j++) {
                houses.get(i).add(createField(null, table.getHouses().get(i)));
            }
            houses.get(i).get(0).setImage(service.getWhiteImage());
        }
    }

    public void handleClickAction() throws Exception {
        LOG.info("Turn ended");
        Service.getInstance().setMyTurn(false);
        Service.getInstance().markCurrentPlayer(this);
        Service.getInstance().getClient().endTurn();
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

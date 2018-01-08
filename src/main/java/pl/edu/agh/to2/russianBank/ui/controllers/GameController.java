package pl.edu.agh.to2.russianBank.ui.controllers;

import com.google.common.collect.Lists;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.edu.agh.to2.russianBank.game.*;
import pl.edu.agh.to2.russianBank.ui.controllers.Service;

import java.io.File;
import java.net.URL;
import java.util.*;

public class GameController implements Initializable {
    private static final Logger LOG = LogManager.getLogger();

    @FXML
    public GridPane gridPane;
    public RowConstraints row1;
    public ColumnConstraints col1;

    private GameTable table;

    private Map<Integer, ImageView> foundations = new HashMap<>();
    private Map<Integer, ImageView> hands = new HashMap<>();
    private Map<Integer, ImageView> wastes = new HashMap<>();
    private Map<Integer, List<ImageView>> houses = new HashMap<>();

    private Service service = new Service();

    @FXML
    public AnchorPane rootPane;

    public void initialize() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        service.buildPictureName(CardRank.KING, CardSuit.HEARTS);
        Image image1 = service.createImage("karty/Gora1.png");
        Image image2 = service.getWhiteImage();
        Image image4 = service.createImage("karty/Gora2.png");

        for (int i = 0; i < 8; i++) {
            foundations.put(i, createField(image2));
        }
        for (int i = 0; i < 2; i++) {
            wastes.put(i,createField(image2));
        }
        hands.put(0, createField(image1));
        hands.put(1, createField(image4));

        for (int i = 0; i < 8; i++) {
            houses.put(i, new ArrayList<>());
            for (int j = 0; j < 9; j++) {
                houses.get(i).add(createField(null));
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

        initializeButton();

        List<PlayerDeck> playerDecks = new ArrayList<>();
        List<House> houses = new ArrayList<>();
        List<Foundation> foundations = new ArrayList<>();
        playerDecks.add(new PlayerDeck(new Hand(new ArrayList<>()), new Waste()));
        playerDecks.add(new PlayerDeck(new Hand(new ArrayList<>()), new Waste()));

        houses.add(new House(new ArrayList<>()));
        houses.add(new House(new ArrayList<>()));
        houses.add(new House(new ArrayList<>()));
        houses.add(new House(new ArrayList<>()));
        houses.add(new House(new ArrayList<>()));
        houses.add(new House(new ArrayList<>()));
        houses.add(new House(new ArrayList<>()));
        houses.add(new House(new ArrayList<>()));

        foundations.add(new Foundation(new ArrayList<>()));
        foundations.add(new Foundation(new ArrayList<>()));
        foundations.add(new Foundation(new ArrayList<>()));
        foundations.add(new Foundation(new ArrayList<>()));
        foundations.add(new Foundation(new ArrayList<>()));
        foundations.add(new Foundation(new ArrayList<>()));
        foundations.add(new Foundation(new ArrayList<>()));
        foundations.add(new Foundation(new ArrayList<>()));

        setTable(new GameTable(playerDecks, houses, foundations));
    }

    private void addImageViews(int row, int minColumn, List<ImageView> images) {
        for (int i = 0; i < images.size(); i++) {
            GridPane.setConstraints(images.get(i), minColumn + i, row);
        }
    }



    private void initializeButton() {
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
        toggle.setOnAction(event -> LOG.debug("Hello World!"));
        gridPane.add(hbBtn, 25, 11);
    }

    private ImageView createField(Image image) {
        ImageView field = new ImageView(image);
        field.fitWidthProperty().bind(gridPane.widthProperty().multiply(col1.getPercentWidth()).divide(100));
        field.fitHeightProperty().bind(gridPane.heightProperty().multiply(row1.getPercentHeight()).divide(100));
        field.setPreserveRatio(true);
        return field;
    }


    @FXML
    public void uncoverCardFromStack() {
        LOG.debug("Clicked!"); // change functionality
    }

    public void setTable(GameTable table) {
        this.table = table;
        for (int i = 0; i < table.getHouses().size(); i++) {
            House house = table.getHouses().get(i);
            houses.get(i).forEach(iv -> iv.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                house.handleMouseClicked();
                event.consume();
            }));
            final int index = i;
            house.addListener(c -> {
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
            });
        }

        for (int i = 0; i < table.getFoundations().size(); i++) {
            Foundation foundation = table.getFoundations().get(i);
            foundations.get(i).addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                foundation.handleMouseClicked();
                event.consume();
            });
            final int index = i;
            foundation.addListener(c -> {
                Optional<Card> card = foundation.takeTopCard();
                ImageView imageView = foundations.get(index);
                imageView.setImage(card.map(e -> service.getImageForCard(e)).orElse(service.getWhiteImage()));
            });
        }

        for (int i = 0; i < table.getPlayers().size(); i++) {
            addListenersForPlayer(i);
        }

        new Thread(() -> {
            try {
                Thread.sleep(1000);
                table.getHouses().get(7).putCard(new Card(CardSuit.DIAMONDS, CardRank.CARD_7));
                table.getPlayers().get(0).getHand().putCard(new Card(CardSuit.HEARTS, CardRank.ACE));

                for (int i = 0; i < 6; i++) {
                    Thread.sleep(1111);
                    table.getHouses().get(3).putCard(new Card(CardSuit.CLUBS, CardRank.CARD_9));
                }
                table.getHouses().get(3).putCard(new Card(CardSuit.DIAMONDS, CardRank.CARD_8));
            } catch (Exception e) {
                LOG.error(e);
            }
        }).start();
    }


    private void addListenersForPlayer(int playerId) {
        Hand hand = table.getPlayers().get(playerId).getHand();
        hands.get(playerId).addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            hand.handleMouseClicked();
            event.consume();
        });
        Waste waste = table.getPlayers().get(playerId).getWaste();
        wastes.get(playerId).addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            waste.handleMouseClicked();
            event.consume();
        });
        waste.addListener(c -> {
            Optional<Card> card = waste.takeTopCard();
            ImageView imageView = wastes.get(playerId);
            imageView.setImage(card.map(e -> service.getImageForCard(e)).orElse(service.getWhiteImage()));
        });
        hand.addListener(c -> {
            Optional<Card> card = hand.takeTopCard();
            ImageView imageView = hands.get(playerId);
            imageView.setImage(card.map(e -> service.getImageForCard(e)).orElse(service.getWhiteImage()));
        });
    }

    //brakuje jeszcze funkcji odsłaniającej kartę ze stosika,  hand, impl tylko po naszej stronie

}

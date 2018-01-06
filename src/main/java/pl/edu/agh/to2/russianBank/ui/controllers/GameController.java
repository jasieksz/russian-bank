package pl.edu.agh.to2.russianBank.ui.controllers;

import com.google.common.collect.Lists;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import pl.edu.agh.to2.russianBank.game.*;

import java.io.File;
import java.net.URL;
import java.util.*;

public class GameController implements Initializable {

    @FXML
    public GridPane gridPane;
    public RowConstraints row1;
    public ColumnConstraints col1;

    private GameTable table;

    private Map<Integer, ImageView> foundations = new HashMap<>();
    private Map<Integer, ImageView> hands = new HashMap<>();
    private Map<Integer, ImageView> wastes = new HashMap<>();
    private Map<Integer, List<ImageView>> houses = new HashMap<>();

    @FXML
    public AnchorPane rootPane;

    public void initialize() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        buildPictureName(CardRank.KING, CardSuit.HEARTS);
        Image image1 = createImage("resources/Karty/Gora1.jpg");
        Image image2 = getWhiteImage();
        Image image4 = createImage("resources/Karty/Gora2.jpg");

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
            houses.get(i).get(0).setImage(getWhiteImage());
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

//        List<PlayerDeck> playerDecks = new ArrayList<>();
//        List<House> houses = new ArrayList<>();
//        List<Foundation> foundations = new ArrayList<>();
//        playerDecks.add(new PlayerDeck(new Hand(new ArrayList<>()), new Waste()));
//        playerDecks.add(new PlayerDeck(new Hand(new ArrayList<>()), new Waste()));
//
//        houses.add(new House(new ArrayList<>()));
//        houses.add(new House(new ArrayList<>()));
//        houses.add(new House(new ArrayList<>()));
//        houses.add(new House(new ArrayList<>()));
//        houses.add(new House(new ArrayList<>()));
//        houses.add(new House(new ArrayList<>()));
//        houses.add(new House(new ArrayList<>()));
//        houses.add(new House(new ArrayList<>()));
//
//        foundations.add(new Foundation(new ArrayList<>()));
//        foundations.add(new Foundation(new ArrayList<>()));
//        foundations.add(new Foundation(new ArrayList<>()));
//        foundations.add(new Foundation(new ArrayList<>()));
//        foundations.add(new Foundation(new ArrayList<>()));
//        foundations.add(new Foundation(new ArrayList<>()));
//        foundations.add(new Foundation(new ArrayList<>()));
//        foundations.add(new Foundation(new ArrayList<>()));
//
//        setTable(new GameTable(playerDecks, houses, foundations));
    }

    private void addImageViews(int row, int minColumn, List<ImageView> images) {
        for (int i = 0; i < images.size(); i++) {
            GridPane.setConstraints(images.get(i), minColumn + i, row);
        }
    }

    private Image createImage(String pathname) {
        File file6 = new File(pathname);
        return new Image(file6.toURI().toString());
    }

    private void initializeButton() {
        Image image6 = createImage("resources/Karty/budzik.png");

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
        toggle.setOnAction(event -> System.out.println("Hello World!"));
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
        System.out.println("Clicked!"); // change functionality
    }

    public void setTable(GameTable table) {
        this.table = table;
        for (int i = 0; i < table.getHouses().size(); i++) {
            House house = table.getHouses().get(i);
            final int index = i;
            house.addListener(c -> {
                List<Card> card = house.getCards();
                for (int j = 0; j < houses.get(index).size(); j++) {
                    if (j < card.size()) {
                        houses.get(index).get(j).setImage(getImageForCard(card.get(j)));
                    } else {
                        houses.get(index).get(j).setImage(null);
                    }
                }

                if (card.isEmpty()) {
                    houses.get(index).get(0).setImage(getWhiteImage());
                }
            });
        }

        for (int i = 0; i < table.getFoundations().size(); i++) {
            Foundation foundation = table.getFoundations().get(i);
            final int index = i;
            foundation.addListener(c -> {
                Optional<Card> card = foundation.takeTopCard();
                ImageView imageView = foundations.get(index);
                imageView.setImage(card.map(this::getImageForCard).orElse(getWhiteImage()));
            });
        }

        for (int i = 0; i < table.getPlayers().size(); i++) {
            addListenersForPlayer(i);
        }

//        new Thread(() -> {
//            try {
//                Thread.sleep(1000);
//                table.getHouses().get(7).putCard(new Card(CardSuit.DIAMONDS, CardRank.CARD_7));
//                table.getPlayers().get(0).getHand().putCard(new Card(CardSuit.HEARTS, CardRank.ACE));
//                System.out.println();
//
//                for (int i = 0; i < 7; i++) {
//                    Thread.sleep(1111);
//                    table.getHouses().get(3).putCard(new Card(CardSuit.CLUBS, CardRank.CARD_9));
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }).start();
    }

    private Image getImageForCard(Card card) {
        String picture = buildPictureName(card.getRank(), card.getSuit());
        return createImage("resources/Karty/" + picture + ".jpg");
    }

    private Image getWhiteImage() {
        return createImage("resources/Karty/White.jpg");
    }

    private void addListenersForPlayer(int playerId) {
        Hand hand = table.getPlayers().get(playerId).getHand();
        Waste waste = table.getPlayers().get(playerId).getWaste();
        waste.addListener(c -> {
            Optional<Card> card = waste.takeTopCard();
            ImageView imageView = wastes.get(playerId);
            imageView.setImage(card.map(this::getImageForCard).orElse(getWhiteImage()));
        });
        hand.addListener(c -> {
            Optional<Card> card = hand.takeTopCard();
            ImageView imageView = hands.get(playerId);
            imageView.setImage(card.map(this::getImageForCard).orElse(getWhiteImage()));
        });
    }

    //brakuje jeszcze funkcji odsłaniającej kartę ze stosika,  hand, impl tylko po naszej stronie
    public String buildPictureName(CardRank r, CardSuit s) {
        String result = s.getRank() + "_" + r.getRankName();
        System.out.println(result);
        return result;
    }
}

package pl.edu.agh.to2.russianBank.ui.controllers;

import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import pl.edu.agh.to2.russianBank.game.*;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {


    @FXML
    public ImageView stackA;

    @FXML
    public ImageView stackB;

    @FXML
    public ImageView binA;
    @FXML
    public ImageView binB;

    @FXML
    private List<Pane> paneList ;

    @FXML
    public ImageView field1;
    @FXML
    public ImageView field2;
    @FXML
    public ImageView field3;
    @FXML
    public ImageView field4;
    @FXML
    public ImageView field5;
    @FXML
    public ImageView field6;
    @FXML
    public ImageView field7;
    @FXML
    public ImageView field8;
    @FXML
    public ImageView field9;
    @FXML
    public ImageView field10;
    @FXML
    public ImageView field11;
    @FXML
    public ImageView field12;
    @FXML
    public ImageView field13;
    @FXML
    public ImageView field14;
    @FXML
    public ImageView field15;
    @FXML
    public ImageView field16;
    //tu nie FXML
    private List<ImageView> imagesList;
    private boolean firstChosen;
    private ImageView firstChosenCard;

    public String field1ID;
    public String field2ID;
    public Field selectedField1;
    public Field selectedField2;
    public Image imageToSet;
    public Integer position1;
    public Integer position2;


    @FXML
    public AnchorPane rootPane;


    @FXML
    public StackPane stackPane;


    public void initialize() {
        int count = 1 ;

        stackPane.setPrefSize(800,800);
        for (ImageView images : imagesList) {
            //pane.setText("Message " + (count++) );
        }
        firstChosen = false;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imagesList = new ArrayList<ImageView>() {{add(field1); add(field2); add(field3); add(field4); add(field5); add(field6); add(field7); add(field8);
        add(field9); add(field10); add(field11); add(field12); add(field13); add(field14); add(field15); add(field16);}};
        //stackPane.setPrefSize(rootPane.getPrefWidth(),rootPane.getPrefHeight());
        rootPane.boundsInParentProperty();
        Parent scene = rootPane.getParent();

        Bounds boundsInScreen = rootPane.localToScreen(rootPane.getBoundsInLocal());

        //scene.sceneToLocal(rootPane);
    }
/*
    @FXML
    public void getPositionToMove(MouseEvent mouseEvent) throws IOException{

        if(!firstChosen) {

            firstChosenCard = (ImageView)mouseEvent.getSource();
            field1ID = firstChosenCard.getId();
            position1 = Integer.parseInt(field1ID.replaceAll("[\\D]", ""));

        }else {
            ImageView secondlyChosenCard = (ImageView)mouseEvent.getSource();
            field2ID = secondlyChosenCard.getId();
            position2 = Integer.parseInt(field2ID.replaceAll("[\\D]", ""));
            ?.tryToMoveCard(position1,position2);
        }

        firstChosen = !firstChosen;

    }

    @FXML
    public void moveCard(pozycja x, pozycja y){
        // metoda wywolywana na nas
        // implementacja podobna do changeCard
    }*/


    @FXML
    public void changeCard(MouseEvent mouseEvent) throws IOException {

        try {
            if(!firstChosen) {
                firstChosenCard = (ImageView)mouseEvent.getSource();

                File file = new File("resources/Karty/Góra2.jpg");
                Image image = new Image(file.toURI().toString());
                ImageView imageView = new ImageView(image);

                imageToSet =firstChosenCard.getImage();
                field1ID = firstChosenCard.getId();
                selectedField1 = GameController.class.getField(field1ID);

                ScaleTransition st = new ScaleTransition(Duration.millis(1000), firstChosenCard);
                st.setByX(-0.1f);
                st.setByY(-0.1f);
                st.setCycleCount(2);
                st.setAutoReverse(true);

                st.play();
                System.out.println(selectedField1);


//                ImageView i = (ImageView) selectedField1.get(this);
//                i.setImage(image);
                //GameController game = new GameController();
                //selectedField1.set(game,imageView);
                // /System.out.println(this.field3);
                //System.out.println(this.field4);
               // System.out.println(field1);

            }
            else {

              //  File file = new File("resources/Karty/Góra2.jpg");
               // Image image = new Image(file.toURI().toString());
                ImageView secondlyChosenCard = (ImageView)mouseEvent.getSource();
                field2ID = secondlyChosenCard.getId();
                selectedField2 = GameController.class.getField(field2ID);
              //  String s = selectedField1.getName();
                ScaleTransition st = new ScaleTransition(Duration.millis(1000), secondlyChosenCard);
                st.setByX(-0.1f);
                st.setByY(-0.1f);
                st.setCycleCount(2);
                st.setAutoReverse(true);

                st.play();
                ImageView i = (ImageView) selectedField2.get(this);
                i.imageProperty().set(imageToSet);

            }
            firstChosen = !firstChosen;

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }


    @FXML
    public void uncoverCardFromStack(){

        //this.stackA.setPickOnBounds(true);
        this.stackA.setOnMouseClicked((MouseEvent e) -> {
            File file = new File("resources/Karty/K_2.jpg");
            System.out.println("Clicked!"); // change functionality
            Image image = new Image(file.toURI().toString());
            this.stackA.imageProperty().set(image);
            //this.stackA.setImage(image);
        });
    }

    public void updateWidthConstaints(double width) {
        // roughly give to the list 66% while to the button 33% of available
        // space, besides paddings.
        // +5s are for extra padding
        AnchorPane.setRightAnchor(stackPane, width * 1 / 3 + 5);
        //AnchorPane.setLeftAnchor(, width * 2 / 3 + 5);
    }


    public void updateCards(GameTable table) {

        List<House> house = table.getHouses();
        List<Foundation> foundations = table.getFoundations();
        List<PlayerDeck> playersCards = table.getPlayers();
        PlayerDeck player1 = playersCards.get(0);
        PlayerDeck player2 = playersCards.get(1);
        Hand hand1 = player1.getHand();
        Hand hand2 = player1.getHand();
        Waste waste1 = player1.getWaste();
        Waste waste2 = player1.getWaste();


    }


}

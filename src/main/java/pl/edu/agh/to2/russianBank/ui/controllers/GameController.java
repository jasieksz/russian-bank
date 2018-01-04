package pl.edu.agh.to2.russianBank.ui.controllers;

import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import pl.edu.agh.to2.russianBank.game.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    @FXML
    public GridPane gridPane;
    public RowConstraints row1;
    public ColumnConstraints col1;

    public ImageView field1;
    public ImageView field2;
    public ImageView field3;
    public ImageView field4;
    public ImageView field5;
    public ImageView field6;
    public ImageView field7;
    public ImageView field8;
    public ImageView field9;
    public ImageView field10;
    public ImageView field11;
    public ImageView field12;
    public ImageView field13;
    public ImageView field14;
    public ImageView field15;
    public ImageView field16;
    public ImageView field17;
    public ImageView field18;
    public ImageView field19;
    public ImageView field20;

    private List<Pane> paneList;
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

    public void initialize() {
        int count = 1;


        for (ImageView images : imagesList) {
            //pane.setText("Message " + (count++) );
        }
        firstChosen = false;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

       /* imagesList = new ArrayList<ImageView>(); {add(field3); add(field4); add(field5); add(field6); add(field7); add(field8);
            add(field9); add(field10); add(field11); add(field12); add(field13); add(field14); add(field15); add(field16); add(field17);add(field18);}; cos tu nie dziala, ta metoda add*/

    File file1 = new File("resources/Karty/Gora1.jpg");
    Image image1 = new Image(file1.toURI().toString());
    File file2 = new File("resources/Karty/White.jpg");
    Image image2 = new Image(file2.toURI().toString());
    File file3 = new File("resources/Karty/Ecru.jpg");
    Image image3 = new Image(file3.toURI().toString());
    File file4 = new File("resources/Karty/Gora2.jpg");
    Image image4 = new Image(file4.toURI().toString());


    field1 = new ImageView(image1);
        field1.fitWidthProperty().bind(gridPane.widthProperty().multiply(col1.getPercentWidth()).divide(100));
        field1.fitHeightProperty().bind(gridPane.heightProperty().multiply(row1.getPercentHeight()).divide(100));

        field1.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                field1.setImage(image2);

            }
        });

    field2 = new ImageView(image2);
        field2.fitWidthProperty().bind(gridPane.widthProperty().multiply(col1.getPercentWidth()).divide(100));
        field2.fitHeightProperty().bind(gridPane.heightProperty().multiply(row1.getPercentHeight()).divide(100));


    field3 = new ImageView(image2);
        field3.fitWidthProperty().bind(gridPane.widthProperty().multiply(col1.getPercentWidth()).divide(100));
        field3.fitHeightProperty().bind(gridPane.heightProperty().multiply(row1.getPercentHeight()).divide(100));


    field4 = new ImageView(image2);
        field4.fitWidthProperty().bind(gridPane.widthProperty().multiply(col1.getPercentWidth()).divide(100));
        field4.fitHeightProperty().bind(gridPane.heightProperty().multiply(row1.getPercentHeight()).divide(100));


    field5 = new ImageView(image2);
        field5.fitWidthProperty().bind(gridPane.widthProperty().multiply(col1.getPercentWidth()).divide(100));
        field5.fitHeightProperty().bind(gridPane.heightProperty().multiply(row1.getPercentHeight()).divide(100));


    field6 = new ImageView(image2);
        field6.fitWidthProperty().bind(gridPane.widthProperty().multiply(col1.getPercentWidth()).divide(100));
        field6.fitHeightProperty().bind(gridPane.heightProperty().multiply(row1.getPercentHeight()).divide(100));


    field7 = new ImageView(image3);
        field7.fitWidthProperty().bind(gridPane.widthProperty().multiply(col1.getPercentWidth()).divide(100));
        field7.fitHeightProperty().bind(gridPane.heightProperty().multiply(row1.getPercentHeight()).divide(100));


    field8 = new ImageView(image3);
        field8.fitWidthProperty().bind(gridPane.widthProperty().multiply(col1.getPercentWidth()).divide(100));
        field8.fitHeightProperty().bind(gridPane.heightProperty().multiply(row1.getPercentHeight()).divide(100));


    field9 = new ImageView(image3);
        field9.fitWidthProperty().bind(gridPane.widthProperty().multiply(col1.getPercentWidth()).divide(100));
        field9.fitHeightProperty().bind(gridPane.heightProperty().multiply(row1.getPercentHeight()).divide(100));


    field10 = new ImageView(image3);
        field10.fitWidthProperty().bind(gridPane.widthProperty().multiply(col1.getPercentWidth()).divide(100));
        field10.fitHeightProperty().bind(gridPane.heightProperty().multiply(row1.getPercentHeight()).divide(100));


    field11 = new ImageView(image3);
        field11.fitWidthProperty().bind(gridPane.widthProperty().multiply(col1.getPercentWidth()).divide(100));
        field11.fitHeightProperty().bind(gridPane.heightProperty().multiply(row1.getPercentHeight()).divide(100));


    field12 = new ImageView(image3);
        field12.fitWidthProperty().bind(gridPane.widthProperty().multiply(col1.getPercentWidth()).divide(100));
        field12.fitHeightProperty().bind(gridPane.heightProperty().multiply(row1.getPercentHeight()).divide(100));

    field13 = new ImageView(image3);
        field13.fitWidthProperty().bind(gridPane.widthProperty().multiply(col1.getPercentWidth()).divide(100));
        field13.fitHeightProperty().bind(gridPane.heightProperty().multiply(row1.getPercentHeight()).divide(100));



    field14 = new ImageView(image3);
        field14.fitWidthProperty().bind(gridPane.widthProperty().multiply(col1.getPercentWidth()).divide(100));
        field14.fitHeightProperty().bind(gridPane.heightProperty().multiply(row1.getPercentHeight()).divide(100));


    field15 = new ImageView(image2);
        field15.fitWidthProperty().bind(gridPane.widthProperty().multiply(col1.getPercentWidth()).divide(100));
        field15.fitHeightProperty().bind(gridPane.heightProperty().multiply(row1.getPercentHeight()).divide(100));

    field16 = new ImageView(image2);
        field16.fitWidthProperty().bind(gridPane.widthProperty().multiply(col1.getPercentWidth()).divide(100));
        field16.fitHeightProperty().bind(gridPane.heightProperty().multiply(row1.getPercentHeight()).divide(100));


    field17 = new ImageView(image2);
        field17.fitWidthProperty().bind(gridPane.widthProperty().multiply(col1.getPercentWidth()).divide(100));
        field17.fitHeightProperty().bind(gridPane.heightProperty().multiply(row1.getPercentHeight()).divide(100));


    field18 = new ImageView(image2);
        field18.fitWidthProperty().bind(gridPane.widthProperty().multiply(col1.getPercentWidth()).divide(100));
        field18.fitHeightProperty().bind(gridPane.heightProperty().multiply(row1.getPercentHeight()).divide(100));


    field19 = new ImageView(image2);
        field19.fitWidthProperty().bind(gridPane.widthProperty().multiply(col1.getPercentWidth()).divide(100));
        field19.fitHeightProperty().bind(gridPane.heightProperty().multiply(row1.getPercentHeight()).divide(100));


    field20 = new ImageView(image4);
        field20.fitWidthProperty().bind(gridPane.widthProperty().multiply(col1.getPercentWidth()).divide(100));
        field20.fitHeightProperty().bind(gridPane.heightProperty().multiply(row1.getPercentHeight()).divide(100));


        field1.setPreserveRatio(true);
        field2.setPreserveRatio(true);
        field3.setPreserveRatio(true);
        field4.setPreserveRatio(true);
        field5.setPreserveRatio(true);
        field6.setPreserveRatio(true);
        field7.setPreserveRatio(true);
        field8.setPreserveRatio(true);
        field9.setPreserveRatio(true);
        field10.setPreserveRatio(true);
        field11.setPreserveRatio(true);
        field12.setPreserveRatio(true);
        field13.setPreserveRatio(true);
        field14.setPreserveRatio(true);
        field15.setPreserveRatio(true);
        field16.setPreserveRatio(true);
        field17.setPreserveRatio(true);
        field18.setPreserveRatio(true);
        field19.setPreserveRatio(true);
        field20.setPreserveRatio(true);

        gridPane.getChildren().addAll(field1,field2,field3,field4, field5, field6,
                field7,field8,field9,field10,field11,field12, field13, field14, field15,
                field16,field17,field18,field19,field20);
        GridPane.setConstraints(field1, 0,11);
        GridPane.setConstraints(field2,1,11);
        GridPane.setConstraints(field3,11,3);
        GridPane.setConstraints(field4,11,5);
        GridPane.setConstraints(field5,11,7);
        GridPane.setConstraints(field6,11,9);
        GridPane.setConstraints(field7,12,3);
        GridPane.setConstraints(field8,12,5);
        GridPane.setConstraints(field9,12,7);
        GridPane.setConstraints(field10,12,9);
        GridPane.setConstraints(field11,14,3);
        GridPane.setConstraints(field12,14,5);
        GridPane.setConstraints(field13,14,7);
        GridPane.setConstraints(field14,14,9);
        GridPane.setConstraints(field15,15,3);
        GridPane.setConstraints(field16,15,5);
        GridPane.setConstraints(field17,15,7);
        GridPane.setConstraints(field18,15,9);
        GridPane.setConstraints(field19,25,1);
        GridPane.setConstraints(field20,26,1);

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
        this.field1.setOnMouseClicked((MouseEvent e) -> {
            File file = new File("resources/Karty/K_2.jpg");
            System.out.println("Clicked!"); // change functionality
            Image image = new Image(file.toURI().toString());
            this.field1.imageProperty().set(image);
            //this.stackA.setImage(image);
        });
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




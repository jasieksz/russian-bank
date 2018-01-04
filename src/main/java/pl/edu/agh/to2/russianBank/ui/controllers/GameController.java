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
import pl.edu.agh.to2.russianBank.game.command.Move;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    public String type1;
    public String type2;
    private GameTable table;


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


        field1.setId("hand1");
        field2.setId("waste2");
        field3.setId("hause3");
        field4.setId("hause4");
        field5.setId("hause5");
        field6.setId("hause6");
        field7.setId("foundation7");
        field8.setId("foundation8");
        field9.setId("foundation9");
        field10.setId("foundation10");
        field11.setId("foundation11");
        field12.setId("foundation12");
        field13.setId("foundation13");
        field14.setId("foundation14");
        field15.setId("hause15");
        field16.setId("hause16");
        field17.setId("hause17");
        field18.setId("hause18");
        field19.setId("waste19");
        field20.setId("hand20");

        List<ImageView> imageViewList = new ArrayList<>();
        imageViewList.add(field1);
        imageViewList.add(field2);
        imageViewList.add(field3);
        imageViewList.add(field4);
        imageViewList.add(field5);
        imageViewList.add(field6);
        imageViewList.add(field7);
        imageViewList.add(field8);
        imageViewList.add(field9);
        imageViewList.add(field10);
        imageViewList.add(field11);
        imageViewList.add(field12);
        imageViewList.add(field13);
        imageViewList.add(field14);
        imageViewList.add(field15);
        imageViewList.add(field16);
        imageViewList.add(field17);
        imageViewList.add(field18);
        imageViewList.add(field19);
        imageViewList.add(field20);



        EventHandler<MouseEvent> getPositionToMove = new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvent) {

                if(!firstChosen) {

                    firstChosenCard = (ImageView) mouseEvent.getSource();
                    field1ID = firstChosenCard.getId();
                    type1 = field1ID.replaceAll("\\d","");
                    System.out.println(type1);
                    position1 = Integer.parseInt(field1ID.replaceAll("[\\D]", ""));
                    System.out.println(position1);


                }else {
                    ImageView secondlyChosenCard = (ImageView)mouseEvent.getSource();
                    field2ID = secondlyChosenCard.getId();
                    type2 = field2ID.replaceAll("\\d","");
                    System.out.println(type2);
                    position2 = Integer.parseInt(field2ID.replaceAll("[\\D]", ""));
                    System.out.println(position2);
                    Move move = new Move(getProperCard(type1,position1),getProperCard(type2,position2));
        //            System.out.println(move.getSource() + "  ....  " + move.getTarget());
                }

                firstChosen = !firstChosen;
            }
        };

        for (ImageView i:imageViewList) {
            i.setOnMouseClicked(getPositionToMove);
            i.setPreserveRatio(true);
        }



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
    public ICardSet getProperCard(String type, Integer position){
        ICardSet card = null;
        switch (type){
            case "foundation":
                for (Foundation f:table.getFoundations()) {
                    if(f.getPosition().equals(position)){
                        card = f;
                    }
                }
                break;
            case "hause":
                for (House h:table.getHouses()) {
                    if(h.getPosition().equals(position)){
                        card = h;
                    }
                }
                break;
            case "waste":
                if(position.equals(2)) card = table.getPlayers().get(0).getWaste();
                else card = table.getPlayers().get(1).getWaste();
                break;
            case "hand":
                if (position.equals(1)) card = table.getPlayers().get(0).getHand();
                else card = table.getPlayers().get(1).getHand();


        }
        return card;
    }


/*    @FXML
    public void moveCard(pozycja x, pozycja y){
        // metoda wywolywana na nas
        // implementacja podobna do changeCard
    }*/

    @FXML
    public void uncoverCardFromStack(){

        //this.stackA.setPickOnBounds(true);
        //this.field1.setOnMouseClicked((MouseEvent e) -> {
            File file = new File("resources/Karty/K_2.jpg");
            System.out.println("Clicked!"); // change functionality
            Image image = new Image(file.toURI().toString());
            //this.field1.imageProperty().set(image);
            //this.stackA.setImage(image);
        //});
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

        //teraz już mamy wszystko -> ustawić to co trzeba, czyli chyba
        // tylko hand i waste

       //Optional<Card> card1 =
        CardSuit s;
        CardRank r;
        //hand1.takeTopCard().ifPresent
        //        (c -> {s = c.getSuit(); r = c.getRank()});
        //field1.setImage

    }


}




package pl.edu.agh.to2.russianBank.ui.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.File;
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
    private ImageView p1 ;
    @FXML
    public ImageView p2;
    @FXML
    public ImageView p3;
    @FXML
    public ImageView p4;
    @FXML
    public ImageView p5;
    @FXML
    public ImageView p6;
    @FXML
    public ImageView p7;
    @FXML
    public ImageView p8;
    @FXML
    public ImageView p9;
    @FXML
    public ImageView p10;
    @FXML
    public ImageView p11;
    @FXML
    public ImageView p12;
    @FXML
    public ImageView p13;
    @FXML
    public ImageView p14;
    @FXML
    public ImageView p15;
    @FXML
    public ImageView p16;
    //tu nie FXML
    private List<ImageView> imagesList;
    private boolean firstChosen;
    private ImageView firstChosenCard;

    @FXML
    public AnchorPane rootPane;

    public void initialize() {
        int count = 1 ;


        for (ImageView images : imagesList) {
            //pane.setText("Message " + (count++) );
        }
        firstChosen = false;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imagesList = new ArrayList<ImageView>() {{add(p1); add(p2); add(p3); add(p4); add(p5); add(p6); add(p7); add(p8);
        add(p9); add(p10); add(p11); add(p12); add(p13); add(p14); add(p15); add(p16);}};
    }

    @FXML
    public void changeCard(MouseEvent mouseEvent) throws IOException {

        if(!firstChosen) {
            firstChosenCard = (ImageView)mouseEvent.getSource();
            //tu jeszcze indeks zczytać
        }
        else {
            ImageView secondlyChosenCard = (ImageView)mouseEvent.getSource();

        }
            firstChosen = !firstChosen;
    }


    @FXML
    public void uncoverCardFromStack(){

        //this.stackA.setPickOnBounds(true);
        this.stackA.setOnMouseClicked((MouseEvent e) -> {
            File file = new File("resources/Karty/K_2.jpg");
            System.out.println("Clicked!"); // change functionality
            Image image = new Image(file.toURI().toString());
            this.stackA.setImage(image);
        });
    }
}

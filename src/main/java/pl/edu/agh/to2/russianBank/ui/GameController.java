package pl.edu.agh.to2.russianBank.ui;

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
    private Pane p1 ;
    @FXML
    public Pane p2;
    @FXML
    public Pane p3;
    @FXML
    public Pane p4;
    @FXML
    public Pane p5;
    @FXML
    public Pane p6;
    @FXML
    public Pane p7;
    @FXML
    public Pane p8;
    @FXML
    public Pane p9;
    @FXML
    public Pane p10;
    @FXML
    public Pane p11;
    @FXML
    public Pane p12;
    @FXML
    public Pane p13;
    @FXML
    public Pane p14;
    @FXML
    public Pane p15;
    @FXML
    public Pane p16;
    //tu nie FXML
    private Pane[] paneArray;
    private boolean firstChosen;
    private ImageView firstChosenCard;

    @FXML
    public AnchorPane rootPane;

    public void initialize() {
        int count = 1 ;
        for (Pane pane : paneList) {
            //pane.setText("Message " + (count++) );
        }
        firstChosen = false;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        paneArray = new Pane[]{p1,p1};
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

package pl.edu.agh.to2.russianBank.ui.controllers;

import com.google.common.collect.Lists;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import java.util.List;
import java.util.Map;

public class StyleBuilder {

    public void setPosition(Map<Integer, CardView> foundations, Map<Integer,
            CardView> hands,Map<Integer, CardView> wastes, Map<Integer, List<CardView>> houses) {
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
     * Function set ImageViews in proper position in stage
     *
     * @param images list of CardView e.g. foundations, houses...
     */

    private void addImageViews(int row, int minColumn, List<CardView> images) {
        for (int i = 0; i < images.size(); i++) {
            GridPane.setConstraints(images.get(i), minColumn + i, row);
        }
    }

    public void setLabelsPositions(Label myName, Label opponentName) {
        GridPane.setHalignment(myName, HPos.CENTER);
        GridPane.setValignment(myName, VPos.TOP);
        GridPane.setConstraints(myName, 0, 9, 2, 2);

        GridPane.setHalignment(opponentName, HPos.CENTER);
        GridPane.setValignment(opponentName, VPos.BOTTOM);
        GridPane.setConstraints(opponentName, 25, 3, 2, 2);
    }

}

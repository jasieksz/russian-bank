package pl.edu.agh.to2.russianBank.game;


import javafx.collections.ListChangeListener;
import pl.edu.agh.to2.russianBank.game.command.Move;
import pl.edu.agh.to2.russianBank.game.command.MoveController;

import java.util.Optional;

public abstract class ICardSet {
    private MoveController moveController;
    private ICardSet firstChosenCard;
    private Boolean firstChosen = false;
    public abstract Optional<Card> takeTopCard();
    public abstract Boolean putCard(Card card);
    public abstract Integer getSize();
    public abstract Boolean isVisible();
    public abstract Integer getPosition();
    public abstract Optional<Card> readTopCard();
    public abstract void addListener(ListChangeListener<Card> listener);
    public void handleMouseClicked() {

        if(!firstChosen) {

            firstChosenCard = this;

         /*   field1ID = firstChosenCard.getId();
            type1 = field1ID.replaceAll("\\d","");
            System.out.println(type1);
            position1 = Integer.parseInt(field1ID.replaceAll("[\\D]", ""));
            System.out.println(position1);

            //this.putCard(new Card(CardSuit.DIAMONDS, CardRank.JACK));
*/
        }else {
            Move move = new Move(firstChosenCard.getPosition(), this.getPosition());
            moveController.executeCommand(move);
            //this.putCard(firstChosenCard.takeTopCard().get());
           /* ImageView secondlyChosenCard = (ImageView)mouseEvent.getSource();
            field2ID = secondlyChosenCard.getId();
            type2 = field2ID.replaceAll("\\d","");
            System.out.println(type2);
            position2 = Integer.parseInt(field2ID.replaceAll("[\\D]", ""));
            System.out.println(position2);
            Move move = new Move(getProperCard(type1,position1),getProperCard(type2,position2));
         */   //            System.out.println(move.getSource() + "  ....  " + move.getTarget());
        }
        firstChosen = !firstChosen;
    }
}

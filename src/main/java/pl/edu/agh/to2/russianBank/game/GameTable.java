package pl.edu.agh.to2.russianBank.game;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameTable {
    private List<ICardSet> piles;
    public GameTable(){

    }

    public GameTable(List<ICardSet> piles) {
        this.piles = piles;
    }

    public List<ICardSet> getPiles() {
        return piles;
    }

    public List<ICardSet> getPlayersCards(int playerNr) {

        return playerNr == 0 ? piles.subList(0,2) : piles.subList(2,4);
    }

    /*public List<PlayerDeck> getPlayersCard() {
        return playersCard;
    }*/

    public List<ICardSet> getPlayersCards() {
        return piles.subList(CardSetPosition.HOUSE_1.getPosition(), CardSetPosition.HOUSE_8.getPosition()+1); //tu trzba to zmienic
    }

    public List<ICardSet> getHouses() {
        return piles.subList(CardSetPosition.HOUSE_1.getPosition(), CardSetPosition.HOUSE_8.getPosition()+1); //tu trzba to zmienic
    }

    public List<ICardSet> getFoundations() {
        return piles.subList(CardSetPosition.FOUNDATION_1.getPosition(), CardSetPosition.FOUNDATION_8.getPosition()+1);
    }

    public boolean swapPiles(int handPos, int wastePos){

        if (hand.getSize() == 0){
            hand.getCards().addAll(waste.getCards());
            waste.getCards().clear();
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameTable gameTable = (GameTable) o;
        return /*Objects.equals(playersCard, gameTable.playersCard) &&
                Objects.equals(houses, gameTable.houses) &&
                Objects.equals(foundations, gameTable.foundations) &&*/
                Objects.equals(piles, gameTable.piles);
    }

    @Override
    public int hashCode() {

        return Objects.hash(piles);//Objects.hash(playersCard, houses, foundations, piles);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                /*.add("playersCard", playersCard)
                .add("houses", houses)
                .add("foundations", foundations)*/
                .add("piles", piles)
                .toString();
    }
}

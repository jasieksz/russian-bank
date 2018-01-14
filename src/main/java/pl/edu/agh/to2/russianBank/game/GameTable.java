package pl.edu.agh.to2.russianBank.game;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameTable {
    private List<PlayerDeck> playersCard; // hand & waste
    private List<House> houses;
    private List<Foundation> foundations;
    private List<ICardSet> piles;

    public GameTable(){

    }

    public GameTable(List<PlayerDeck> playersCard, List<House> houses, List<Foundation> foundations) {
        Preconditions.checkArgument(foundations.size() == 8);
        Preconditions.checkArgument(houses.size() == 8);
        Preconditions.checkArgument(playersCard.size() == 2);
        this.playersCard = playersCard;
        this.houses = houses;
        this.foundations = foundations;
    }

    public GameTable(List<Player> players, List<ICardSet> piles) {
        this.playersCard = new ArrayList<>();
        for (Player player : players) {
            this.playersCard.add(player.getPlayerDeck());
        }
        this.piles = piles;
    }

    public List<ICardSet> getPiles() {
        return piles;
    }

    public List<PlayerDeck> getPlayersCard() {
        return playersCard;
    }

    public List<ICardSet> getHouses() {
        return piles.subList(4,12); //tu trzba to zmienic
    }

    public List<ICardSet> getFoundations() {
        return piles.subList(12,20);
    }

    public boolean swapPiles(ICardSet hand, ICardSet waste){
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
        return Objects.equals(playersCard, gameTable.playersCard) &&
                Objects.equals(houses, gameTable.houses) &&
                Objects.equals(foundations, gameTable.foundations) &&
                Objects.equals(piles, gameTable.piles);
    }

    @Override
    public int hashCode() {

        return Objects.hash(playersCard, houses, foundations, piles);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("playersCard", playersCard)
                .add("houses", houses)
                .add("foundations", foundations)
                .add("piles", piles)
                .toString();
    }
}

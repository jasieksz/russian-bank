package pl.edu.agh.to2.russianBank.net.transport;

import com.google.common.base.MoreObjects;

import java.util.Objects;

public class HelloMessage extends Message {
    private String playerName;

    public HelloMessage() {
    }

    public HelloMessage(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HelloMessage that = (HelloMessage) o;
        return Objects.equals(playerName, that.playerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerName);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("playerName", playerName)
                .toString();
    }
}

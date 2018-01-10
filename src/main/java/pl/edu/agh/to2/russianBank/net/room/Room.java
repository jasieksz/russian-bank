package pl.edu.agh.to2.russianBank.net.room;

import pl.edu.agh.to2.russianBank.game.Player;
import pl.edu.agh.to2.russianBank.net.server.PlayerConnection;
import pl.edu.agh.to2.russianBank.net.transport.Message;

import java.util.ArrayList;
import java.util.List;


public class Room {

    private PlayerConnection playerAConn = null;
    private Player playerA;
    private PlayerConnection playerBConn = null;
    private Player playerB;

    public PlayerConnection getPlayerAConn() {
        return playerAConn;
    }

    public PlayerConnection getPlayerBConn() {
        return playerBConn;
    }


    public void setPlayerA(PlayerConnection playerConn, String name) {
        this.playerAConn = playerConn;
        this.playerA = new Player(name);
    }

    public void setPlayerB(PlayerConnection playerConn, String name) {
        this.playerBConn = playerConn;
        this.playerB = new Player(name);
    }

    public List<Player> getPlayers() {
        List<Player> players = new ArrayList<Player>();
        players.add(playerA);
        players.add(playerB);
        return players;
    }

    public boolean isFull() {
        return (playerAConn != null) && (playerBConn != null);
    }

    public void unicast(PlayerConnection sender, Message msg) {
        PlayerConnection receiver = sender.equals(playerAConn) ? playerBConn : playerAConn;
        if (receiver != null)
            receiver.sendMessage(msg);
    }

    public void broadcast(Message msg) {
        if (playerAConn != null)
            playerAConn.sendMessage(msg);
        if (playerBConn != null)
            playerBConn.sendMessage(msg);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        if (playerAConn != null ? !playerAConn.equals(room.playerAConn) : room.playerAConn != null) return false;
        return playerBConn != null ? playerBConn.equals(room.playerBConn) : room.playerBConn == null;
    }

    @Override
    public int hashCode() {
        int result = playerAConn != null ? playerAConn.hashCode() : 0;
        result = 31 * result + (playerBConn != null ? playerBConn.hashCode() : 0);
        return result;
    }

}

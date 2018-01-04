package pl.edu.agh.to2.russianBank.net.room;

import pl.edu.agh.to2.russianBank.net.server.PlayerConnection;
import pl.edu.agh.to2.russianBank.net.transport.Message;


public class Room {

    private PlayerConnection playerA;
    private PlayerConnection playerB;

    public PlayerConnection getPlayerA() {
        return playerA;
    }

    public PlayerConnection getPlayerB() {
        return playerB;
    }

    public void setPlayerA(PlayerConnection player) {
        this.playerA = player;
    }

    public void setPlayerB(PlayerConnection player) {
        this.playerB = player;
    }

    public void unicast(PlayerConnection sender, Message msg) {
        PlayerConnection receiver = sender.equals(playerA)? playerB: playerA;
        if(receiver != null)
            receiver.sendMessage(msg);
    }

    public void broadcast(Message msg) {
        if(playerA != null)
            playerA.sendMessage(msg);
        if(playerB != null)
            playerB.sendMessage(msg);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        if (playerA != null ? !playerA.equals(room.playerA) : room.playerA != null) return false;
        return playerB != null ? playerB.equals(room.playerB) : room.playerB == null;
    }

    @Override
    public int hashCode() {
        int result = playerA != null ? playerA.hashCode() : 0;
        result = 31 * result + (playerB != null ? playerB.hashCode() : 0);
        return result;
    }
}

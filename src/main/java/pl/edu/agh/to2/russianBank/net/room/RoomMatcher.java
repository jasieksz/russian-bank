package pl.edu.agh.to2.russianBank.net.room;

import pl.edu.agh.to2.russianBank.net.server.PlayerConnection;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;


public class RoomMatcher {
    private final ConcurrentHashMap<PlayerConnection, Room> rooms;
    private final ConcurrentLinkedQueue<PlayerConnection> freeMatches;

    public RoomMatcher() {
        rooms = new ConcurrentHashMap<>();
        freeMatches = new ConcurrentLinkedQueue<>();
    }

    /**
     * assign room to a player
     *
     * @param player
     * @return room assigned to a player
     */
    public Room assign(PlayerConnection player, String playerName) {
        PlayerConnection assignedMatch = freeMatches.poll();

        if (assignedMatch == null)
            return createNewRoom(player, playerName);

        Room room = rooms.get(assignedMatch);
        room.setPlayerB(player, playerName);

        rooms.put(player, room);

        return room;
    }

    /**
     * gets room assigned to a player
     *
     * @param player
     * @return room assigned to a player
     */
    public Room getRoom(PlayerConnection player) {
        return rooms.get(player);
    }

    /**
     * removes room for both players
     *
     * @param room
     */
    public void deleteRoom(Room room) {
        freeMatches.removeIf(player -> getRoom(player).equals(room));
        if (room.getPlayerAConn() != null)
            rooms.remove(room.getPlayerAConn());
        if (room.getPlayerBConn() != null)
            rooms.remove(room.getPlayerBConn());
    }

    private Room createNewRoom(PlayerConnection player, String playerName) {
        Room room = new Room();
        room.setPlayerA(player, playerName);

        rooms.put(player, room);
        freeMatches.add(player);

        return room;
    }
}

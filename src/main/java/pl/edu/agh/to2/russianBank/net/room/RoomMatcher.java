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
    public Room assign(PlayerConnection player){
        PlayerConnection assignedMatch = freeMatches.poll();

        if(assignedMatch == null)
            return createNewRoom(player);

        Room room = rooms.get(assignedMatch);
        room.setPlayerB(player);
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
        if(room.getPlayerA() != null)
            rooms.remove(room.getPlayerA());
        if(room.getPlayerB() != null)
            rooms.remove(room.getPlayerB());
    }

    private Room createNewRoom(PlayerConnection player) {
        Room room = new Room();
        room.setPlayerA(player);
        rooms.put(player, room);
        freeMatches.add(player);

        return room;
    }
}

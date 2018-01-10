package pl.edu.agh.to2.russianBank.net.room;


import static org.junit.Assert.*;
import org.junit.Test;
import pl.edu.agh.to2.russianBank.net.server.PlayerConnection;
import pl.edu.agh.to2.russianBank.net.transport.Message;

import java.util.concurrent.CompletableFuture;

public class RoomMatcherTest {
    private class PlayerTest implements PlayerConnection {
        private final int id;
        private String name;

        public PlayerTest(int id) { this.id = id; }

        @Override
        public int getId() { return id; }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public void setName(String name) {
            this.name = name;
        }

        @Override
        public CompletableFuture<Void> sendMessage(Message message) { return null; }
    }

    @Test
    public void testAssign_WhenPlayerIsFirst() {
        RoomMatcher rm = new RoomMatcher();
        PlayerConnection player = new PlayerTest(0);

        Room room = rm.assign(player,"murk");

        assertEquals(room.getPlayerAConn(), player);
    }

    @Test
    public void testAssign_WhenPlayerIsTwoKPlusOne() {
        RoomMatcher rm = new RoomMatcher();
        int even = 200;
        for(int i = 0; i < even; ++i) {
            PlayerConnection player = new PlayerTest(i);
            rm.assign(player, "murk");
        }

        PlayerConnection player = new PlayerTest(even + 1);
        Room room = rm.assign(player, "murk");

        assertEquals(room.getPlayerAConn(), player);
    }

    @Test
    public void testAssign_WhenPlayerIsTwoK() {
        RoomMatcher rm = new RoomMatcher();
        int odd = 199;
        for(int i = 0; i < odd; ++i) {
            PlayerConnection player = new PlayerTest(i);
            rm.assign(player, "murk");
        }

        PlayerConnection player = new PlayerTest(odd + 1);
        Room room = rm.assign(player, "murk");

        assertEquals(room.getPlayerBConn(), player);
    }

    @Test
    public void testDeleteRoom() {
        RoomMatcher rm = new RoomMatcher();
        PlayerConnection playerA = new PlayerTest(0);
        rm.assign(playerA, "murk");
        PlayerConnection playerB = new PlayerTest(1);
        Room room = rm.assign(playerB, "murk");

        rm.deleteRoom(room);

        assertNull(rm.getRoom(playerA));
        assertNull(rm.getRoom(playerB));
    }
}

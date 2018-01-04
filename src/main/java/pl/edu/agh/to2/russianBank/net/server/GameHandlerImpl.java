package pl.edu.agh.to2.russianBank.net.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.edu.agh.to2.russianBank.net.room.Room;
import pl.edu.agh.to2.russianBank.net.room.RoomMatcher;
import pl.edu.agh.to2.russianBank.net.transport.Message;
import pl.edu.agh.to2.russianBank.net.transport.ShutdownMessage;

public class GameHandlerImpl implements GameHandler {
    private static final Logger LOG = LogManager.getLogger();
    private final RoomMatcher roomMatcher = new RoomMatcher();

    @Override
    public void onConnect(PlayerConnection player) {
        LOG.debug("Incoming player {}", player);
        roomMatcher.assign(player);
    }

    @Override
    public void onClose(PlayerConnection player) {
        LOG.debug("Closing player {}", player);
        Room room = roomMatcher.getRoom(player);
        room.unicast(player, new ShutdownMessage());
        roomMatcher.deleteRoom(room);
    }

    @Override
    public void onMessage(PlayerConnection player, Message message) {
        LOG.debug("Message from player {}: {}", player, message);
        // TODO
    }
}

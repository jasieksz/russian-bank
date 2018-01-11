package pl.edu.agh.to2.russianBank.net.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.edu.agh.to2.russianBank.game.Game;
import pl.edu.agh.to2.russianBank.game.GameState;
import pl.edu.agh.to2.russianBank.game.GameTable;
import pl.edu.agh.to2.russianBank.net.UnsupportedMessageException;
import pl.edu.agh.to2.russianBank.net.room.Room;
import pl.edu.agh.to2.russianBank.net.room.RoomMatcher;
import pl.edu.agh.to2.russianBank.net.transport.*;

public class GameHandlerImpl implements GameHandler {
    private static final Logger LOG = LogManager.getLogger();
    private final RoomMatcher roomMatcher = new RoomMatcher();

    @Override
    public void onConnect(PlayerConnection player) {
        LOG.debug("Incoming player {}", player);
    }

    @Override
    public void onClose(PlayerConnection player) {
        LOG.debug("Closing player {}", player);
        Room room = roomMatcher.getRoom(player);
        room.unicast(player, new EndGameMessage(true, "Player has disconnected"));
        roomMatcher.deleteRoom(room);
    }

    @Override
    public void onMessage(PlayerConnection player, Message message) {
        LOG.debug("Message from player {}: {}", player, message);
        message.accept(new Visitor(player));
    }

    private class Visitor implements MessageVisitor {
        private final PlayerConnection player;

        private Visitor(PlayerConnection player) {
            this.player = player;
        }

        @Override
        public void visit(HelloMessage message) {
            LOG.info("Hello {}!", message.getPlayerName());
            Room room = roomMatcher.assign(player, message.getPlayerName());
            LOG.info("room assigned for player {}", message.getPlayerName());

            if (room.isFull()) {
                Game game = new Game(room.getPlayers());

                room.broadcast(new StartGameMessage(new GameState(room.getPlayers(), game.getGameTable())));
            }
        }

        @Override
        public void visit(EndGameMessage message) {
            LOG.debug("Player {} ends game {}, because: {}",
                    player.getName(),
                    (message.isWon() ? "winning" : "losing"),
                    message.getCause());
            Room room = roomMatcher.getRoom(player);
            room.unicast(player, new EndGameMessage(!message.isWon(), message.getCause()));
        }

        @Override
        public void visit(MoveMessage message) {
            LOG.info("move {}", message.getMove());
            roomMatcher.getRoom(player).unicast(player, message);
        }

        @Override
        public void visit(StartGameMessage message) {
            throw new UnsupportedMessageException(message);
        }
    }
}

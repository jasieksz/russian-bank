package pl.edu.agh.to2.russianBank.net.server;

import com.google.gson.JsonParseException;
import io.javalin.embeddedserver.jetty.websocket.WebSocketConfig;
import io.javalin.embeddedserver.jetty.websocket.WebSocketHandler;
import io.javalin.embeddedserver.jetty.websocket.WsSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.websocket.api.Session;
import pl.edu.agh.to2.russianBank.net.transport.Message;
import pl.edu.agh.to2.russianBank.net.transport.MessageSerializer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class GameWS implements WebSocketConfig {
    private static final Logger LOG = LogManager.getLogger();

    private final AtomicInteger idSequence = new AtomicInteger();
    private final Map<Session, PlayerConnection> connectionMap = new ConcurrentHashMap<>();

    private final GameHandler gameHandler = new GameHandlerImpl();

    @Override
    public void configure(WebSocketHandler wsh) {
        wsh.onConnect(this::onConnect);
        wsh.onClose(this::onClose);
        wsh.onMessage(this::onMesssage);
        wsh.onError(this::onError);
    }

    private void onConnect(WsSession session) {
        LOG.debug("Incoming connection...");
        final PlayerConnectionImpl player = new PlayerConnectionImpl(idSequence.incrementAndGet(), session);
        assert !connectionMap.containsKey(session);
        connectionMap.put(session, player);
        gameHandler.onConnect(player);
    }

    private void onClose(WsSession session, int status, String reasonString) {
        final PlayerConnection player = connectionMap.remove(session);
        assert player != null;
        LOG.debug("Closing connection with player id: {}, status: {}, reason: {}", player.getId(), status, reasonString);
        gameHandler.onClose(player);
    }

    private void onMesssage(WsSession session, String msgString) {
        final PlayerConnection player = connectionMap.get(session);
        assert player != null;
        LOG.debug("Message from player id: {}, json: {}", player.getId(), msgString);

        Message message = null;

        try {
            message = MessageSerializer.GLOBAL.deserialize(msgString);
        } catch (JsonParseException ex) {
            LOG.warn("Received invalid payload from player id: {}, error: {}", player.getId(), ex.getMessage());
        }

        if (message != null) {
            gameHandler.onMessage(player, message);
        }
    }

    private void onError(WsSession session, Throwable ex) {
        final PlayerConnection player = connectionMap.remove(session);
        assert player != null;
        LOG.error("WebSocket error from player id: {}", player.getId(), ex);
        gameHandler.onClose(player);
    }
}

package pl.edu.agh.to2.russianBank.net.server;

import io.javalin.embeddedserver.jetty.websocket.WebSocketConfig;
import io.javalin.embeddedserver.jetty.websocket.WebSocketHandler;
import io.javalin.embeddedserver.jetty.websocket.WsSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GameWebSocket implements WebSocketConfig {
    private static final Logger LOG = LogManager.getLogger();

    @Override
    public void configure(WebSocketHandler wsh) {
        wsh.onConnect(this::onConnect);
        wsh.onClose(this::onClose);
        wsh.onMessage(this::onMesssage);
        wsh.onError(this::onError);
    }

    private void onConnect(WsSession session) {
        LOG.debug("Incoming connection...");
    }

    private void onClose(WsSession session, int status, String reasonString) {
        LOG.debug("Closing connection with status: {}, reason: {}", status, reasonString);
    }

    private void onMesssage(WsSession session, String msgString) {
        LOG.debug("Message: {}", msgString);
    }

    private void onError(WsSession session, Throwable ex) {
        LOG.error("WebSocket error", ex);
    }
}

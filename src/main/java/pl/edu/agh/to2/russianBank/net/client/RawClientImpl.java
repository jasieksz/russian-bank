package pl.edu.agh.to2.russianBank.net.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.websocket.client.WebSocketClient;
import pl.edu.agh.to2.russianBank.net.transport.Message;
import pl.edu.agh.to2.russianBank.net.transport.MessageVisitor;

import java.net.URI;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class RawClientImpl implements RawClient {
    private static final Logger LOG = LogManager.getLogger();

    private final WebSocketClient wsClient;
    private final RawClientWS socket;
    private final Set<MessageVisitor> listeners;

    private RawClientImpl(WebSocketClient wsClient, RawClientWS socket, Set<MessageVisitor> listeners) {
        this.wsClient = wsClient;
        this.socket = socket;
        this.listeners = listeners;
    }

    static CompletableFuture<RawClient> connect(URI serverUri) throws Exception {
        final CompletableFuture<Void> connected = new CompletableFuture<>();
        final WebSocketClient wsClient = new WebSocketClient();
        final Set<MessageVisitor> listeners = ConcurrentHashMap.newKeySet(1);
        final RawClientWS socket = new RawClientWS(connected, listeners);

        LOG.debug("Starting WebSocket client");
        wsClient.start();

        LOG.debug("Connecting to {}", serverUri);
        wsClient.connect(socket, serverUri);

        return connected.thenApply(_void -> new RawClientImpl(wsClient, socket, listeners));
    }

    @Override
    public void addListener(MessageVisitor visitor) {
        listeners.add(visitor);
    }

    @Override
    public void removeListener(MessageVisitor visitor) {
        listeners.remove(visitor);
    }

    @Override
    public CompletableFuture<Void> sendMessage(Message message) {
        LOG.debug("Sending {}", message);
        return socket.sendMessage(message);
    }

    @Override
    public boolean awaitClose(int timeout, TimeUnit unit) throws InterruptedException {
        return socket.awaitClose(timeout, unit);
    }

    @Override
    public void close() throws Exception {
        if (wsClient.isStarted()) {
            LOG.debug("Stopping WebSocket client");
            wsClient.stop();
        }
    }
}

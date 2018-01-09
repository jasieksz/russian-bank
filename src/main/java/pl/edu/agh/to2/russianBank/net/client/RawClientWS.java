package pl.edu.agh.to2.russianBank.net.client;

import com.google.gson.JsonParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.*;
import pl.edu.agh.to2.russianBank.net.JettyUtil;
import pl.edu.agh.to2.russianBank.net.transport.Message;
import pl.edu.agh.to2.russianBank.net.transport.MessageSerializer;
import pl.edu.agh.to2.russianBank.net.transport.MessageVisitor;

import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@WebSocket
public class RawClientWS {
    private static final Logger LOG = LogManager.getLogger();

    private final CountDownLatch closeLatch = new CountDownLatch(1);
    private final CompletableFuture<Void> connected;
    private final Set<MessageVisitor> listeners;

    private Session session;

    RawClientWS(CompletableFuture<Void> connected, Set<MessageVisitor> listeners) {
        this.connected = connected;
        this.listeners = listeners;
    }

    @OnWebSocketConnect
    public void onConnect(Session session) {
        LOG.debug("Connected to server");
        this.session = session;
        this.connected.complete(null);
    }

    @OnWebSocketClose
    public void onClose(int status, String reasonString) {
        LOG.debug("Closing connection with status: {}, reason: {}", status, reasonString);
        this.closeLatch.countDown();
    }

    @OnWebSocketMessage
    public void onMessage(String msgString) {
        LOG.debug("Message: {}", msgString);

        Message message = null;

        try {
            message = MessageSerializer.GLOBAL.deserialize(msgString);
        } catch (JsonParseException ex) {
            LOG.warn("Received invalid payload, error: {}", ex.getMessage());
        }

        if (message != null) {
            for (MessageVisitor visitor : listeners) {
                message.accept(visitor);
            }
        }
    }

    @OnWebSocketError
    public void onError(Throwable ex) {
        LOG.error("WebSocket error", ex);
        if (!connected.isDone()) {
            connected.completeExceptionally(ex);
        }
    }

    public boolean awaitClose(int timeout, TimeUnit unit) throws InterruptedException {
        return closeLatch.await(timeout, unit);
    }

    public CompletableFuture<Void> sendMessage(Message message) {
        return sendString(MessageSerializer.GLOBAL.serialize(message));
    }

    private CompletableFuture<Void> sendString(String string) {
        return JettyUtil.sendStringByPromise(session.getRemote(), string)
                .exceptionally(ex -> {
                    LOG.debug(ex);
                    return null;
                });
    }
}

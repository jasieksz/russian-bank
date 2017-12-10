package pl.edu.agh.to2.russianBank.net.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.*;
import pl.edu.agh.to2.russianBank.net.JettyUtil;
import pl.edu.agh.to2.russianBank.net.transport.Message;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@WebSocket
public class RussianBankClientWebSocket {
    private static final Logger LOG = LogManager.getLogger();

    private final CountDownLatch closeLatch = new CountDownLatch(1);
    private final CompletableFuture<Void> connected;

    private Session session;

    RussianBankClientWebSocket(CompletableFuture<Void> connected) {
        this.connected = connected;
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
    }

    @OnWebSocketError
    public void onError(Throwable ex) {
        LOG.error("WebSocket error", ex);
    }

    public boolean awaitClose(int timeout, TimeUnit unit) throws InterruptedException {
        return closeLatch.await(timeout, unit);
    }

    public CompletableFuture<Void> sendMessage(Message message) {
        return sendString(message.serialize());
    }

    private CompletableFuture<Void> sendString(String string) {
        return JettyUtil.sendStringByPromise(session.getRemote(), string)
                .exceptionally(ex -> {
                    LOG.debug(ex);
                    return null;
                });
    }
}

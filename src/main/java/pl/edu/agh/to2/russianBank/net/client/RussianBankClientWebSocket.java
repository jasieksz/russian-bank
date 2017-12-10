package pl.edu.agh.to2.russianBank.net.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.*;
import pl.edu.agh.to2.russianBank.net.JettyUtil;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@WebSocket
public class RussianBankClientWebSocket {
    private static final Logger LOG = LogManager.getLogger();

    private final CountDownLatch closeLatch = new CountDownLatch(1);

    @OnWebSocketConnect
    public void onConnect(Session session) {
        LOG.debug("Connected to server");

        JettyUtil.sendStringByPromise(session.getRemote(), "Hello")
                .exceptionally(ex -> {
                    LOG.error(ex);
                    return null;
                })
                .thenRun(closeLatch::countDown);
    }

    @OnWebSocketClose
    public void onClose(Session session, int status, String reasonString) {
        LOG.debug("Closing connection with status: {}, reason: {}", status, reasonString);
        this.closeLatch.countDown();
    }

    @OnWebSocketMessage
    public void onMessage(Session session, String msgString) {
        LOG.debug("Message: {}", msgString);
    }

    @OnWebSocketError
    public void onError(Session session, Throwable ex) {
        LOG.error("WebSocket error", ex);
    }

    public boolean awaitClose(int timeout, TimeUnit unit) throws InterruptedException {
        return closeLatch.await(timeout, unit);
    }
}

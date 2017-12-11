package pl.edu.agh.to2.russianBank.net.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.websocket.client.WebSocketClient;
import pl.edu.agh.to2.russianBank.net.transport.Message;

import java.net.URI;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class RussianBankClient implements AutoCloseable {
    private static final Logger LOG = LogManager.getLogger();

    private final WebSocketClient wsClient;
    private final RussianBankClientWebSocket socket;

    private RussianBankClient(WebSocketClient wsClient, RussianBankClientWebSocket socket) {
        this.wsClient = wsClient;
        this.socket = socket;
    }

    public static CompletableFuture<RussianBankClient> connect(String serverUri) throws Exception {
        return connect(new URI(serverUri));
    }

    public static CompletableFuture<RussianBankClient> connect(URI serverUri) throws Exception {
        final CompletableFuture<Void> connected = new CompletableFuture<>();
        final WebSocketClient wsClient = new WebSocketClient();
        final RussianBankClientWebSocket socket = new RussianBankClientWebSocket(connected);

        LOG.debug("Starting WebSocket client");
        wsClient.start();

        LOG.debug("Connecting to {}", serverUri);
        wsClient.connect(socket, serverUri);

        return connected.thenApply(_void -> new RussianBankClient(wsClient, socket));
    }

    // TODO: This should become private after higher-level API stabilizes
    public CompletableFuture<Void> sendMessage(Message message) {
        LOG.debug("Sending {}", message);
        return socket.sendMessage(message);
    }

    /**
     * Waits for websocket connection to close.
     *
     * @param timeout the maximum time to wait
     * @param unit    the time unit of the {@code timeout} argument
     * @return {@code true} connection really closed or {@code false}
     * if the waiting time elapsed before closing
     * @throws InterruptedException if the current thread is interrupted while waiting
     */
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

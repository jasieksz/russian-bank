package pl.edu.agh.to2.russianBank.net.client;

import pl.edu.agh.to2.russianBank.net.transport.Message;
import pl.edu.agh.to2.russianBank.net.transport.MessageVisitor;

import java.net.URI;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Low level client interface
 */
public interface RawClient extends AutoCloseable {
    static CompletableFuture<RawClient> connect(String serverUri) throws Exception {
        return connect(new URI(serverUri));
    }

    static CompletableFuture<RawClient> connect(URI serverUri) throws Exception {
        return RawClientImpl.connect(serverUri);
    }

    void addListener(MessageVisitor visitor);

    void removeListener(MessageVisitor visitor);

    CompletableFuture<Void> sendMessage(Message message);

    /**
     * Waits for websocket connection to close.
     *
     * @param timeout the maximum time to wait
     * @param unit    the time unit of the {@code timeout} argument
     * @return {@code true} connection really closed or {@code false}
     * if the waiting time elapsed before closing
     * @throws InterruptedException if the current thread is interrupted while waiting
     */
    boolean awaitClose(int timeout, TimeUnit unit) throws InterruptedException;
}

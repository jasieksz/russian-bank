package pl.edu.agh.to2.russianBank.net.client;

import pl.edu.agh.to2.russianBank.net.transport.Message;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Low level client interface
 */
public interface RawClient extends AutoCloseable {
    /**
     * Add incoming event listener.
     */
    void addListener(RawClientListener listener);

    /**
     * Remove incoming event listener.
     */
    void removeListener(RawClientListener listener);

    /**
     * Send message to server.
     *
     * @param message message object
     * @return a future which completes when message is sent (exits client machine).
     */
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
